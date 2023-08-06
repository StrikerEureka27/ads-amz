package gt.com.ad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import gt.com.ad.data.Adsfile;
import gt.com.ad.service.IAdsFileService;

@Component
public class MyRouteBuilder extends RouteBuilder {

    @Autowired
    IAdsFileService amzadsservices;

    @Override
    public void configure() throws Exception {
        from("jpa://gt.com.ad.data.Adsfile?consumeDelete=false&nativeQuery=select * from repository where step = 1")
                .routeId("xlsx")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        Adsfile f = exchange.getIn().getBody(Adsfile.class);

                        File tmp = File.createTempFile(f.getName(), ".xlsx");

                        try {
                            FileOutputStream fos = new FileOutputStream(tmp);
                            fos.write(f.getFile());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Workbook wb = WorkbookFactory.create(tmp);
                        Sheet sheet1 = wb.getSheetAt(1);

                        Set<Integer> filterStepOne = new HashSet<Integer>();
                        Set<Integer> filterStepTwo = new HashSet<Integer>();
                        Set<Integer> filterStepTree = new HashSet<Integer>();
                        Set<Integer> filterStepFour = new HashSet<Integer>();
                        Set<Integer> filterStepFive = new HashSet<Integer>();

                        String refClick = "";
                        String refOrder = "";
                        
                        // Filtering worbook
                        for (Row row : sheet1) {
                            for (Cell cell : row) {
                                CellReference ref = new CellReference(row.getRowNum(),
                                        cell.getColumnIndex());

                                if (ref.formatAsString().substring(0, 2).equals("AK")) {
                                    refClick = ref.formatAsString().toString();
                                }

                                if (ref.formatAsString().substring(0, 2).equals("AO")) {
                                    refOrder = ref.formatAsString().toString();
                                }

                                if (ref.formatAsString().substring(0, 1).equals("B")) {
                                    if (!cell.getStringCellValue().equals("Keyword")
                                            && !cell.getStringCellValue().equals("Product Targeting")
                                            && !cell.getStringCellValue().equals("Entity")) {
                                        filterStepOne.add(Integer.valueOf(cell.getRowIndex()));
                                    }
                                }

                                if (ref.formatAsString().substring(0, 2).equals("AH")) {

                                    if (cell.getStringCellValue().equals("loose-match")
                                            || cell.getStringCellValue().equals("close-match")
                                            || cell.getStringCellValue().equals("complements")
                                            || cell.getStringCellValue().equals("substitutes")
                                                    && !cell.getStringCellValue()
                                                            .equals("Product Targeting Expression")) {
                                        filterStepTwo.add(Integer.valueOf(cell.getRowIndex()));
                                    }

                                }

                                if (ref.formatAsString().substring(0, 2).equals("AO")) {
                                    if (!cell.getCellType().toString().equals("STRING")) {
                                        if (cell.getNumericCellValue() <= 3.00) {
                                            filterStepTree.add(Integer.valueOf(cell.getRowIndex()));
                                        }
                                    }
                                }

                                if (ref.formatAsString().substring(0, 2).equals("AR")) {
                                    if (!cell.getCellType().toString().equals("STRING")) {
                                        if (cell.getNumericCellValue() >= 0.4000) {
                                            filterStepFour.add(Integer.valueOf(cell.getRowIndex()));
                                        }
                                    }
                                }
                            }

                            if (row.getRowNum() != 0) {
                                if (row.getCell(37) != null || row.getCell(41) != null) {
                                    var res = row.getCell(36).getNumericCellValue() /
                                            row.getCell(40).getNumericCellValue();
                                    log.info(String.format("row index %d res : %f", row.getRowNum(), res));
                                    if (res <= 0.067) {
                                        filterStepFive.add(Integer.valueOf(row.getRowNum()));
                                    }
                                }
                            }

                            if (row.getRowNum() == 0) {
                                row.createCell(row.getLastCellNum() + 1,
                                        CellType.STRING).setCellValue("CVR");
                            } else {
                                row.createCell(row.getLastCellNum() + 1, CellType.FORMULA)
                                        .setCellFormula(String.format("%s/%s", refClick, refOrder));
                            }

                        }

                        for (Integer r : filterStepOne) {
                            Row row = sheet1.getRow(r);
                            if (row != null) {
                                log.info("filterStepOne index delete: " + r);
                                sheet1.removeRow(row);
                            }
                        }

                        for (Integer r : filterStepTwo) {
                            Row row = sheet1.getRow(r);
                            if (row != null) {
                                log.info("filterStepTwo index delete: " + r);
                                sheet1.removeRow(row);
                            }
                        }

                        for (Integer r : filterStepTree) {
                            Row row = sheet1.getRow(r);
                            if (row != null) {
                                log.info("filterStepTree index delete: " + r);
                                sheet1.removeRow(row);
                            }
                        }

                        for (Integer r : filterStepFour) {
                            Row row = sheet1.getRow(r);
                            if (row != null) {
                                log.info("filterStepTFour index delete: " + r);
                                sheet1.removeRow(row);
                            }
                        }

                        for (Integer r : filterStepFive) {
                            Row row = sheet1.getRow(r);
                            if (row != null) {
                                log.info("filterStepTFive index delete: " + r);
                                sheet1.removeRow(row);
                            }
                        }

                        // Saving workbook
                        try {
                            OutputStream fileOut = new FileOutputStream("C://Users//striker//Documents//repository//workbook.xlsx");
                            fileOut.write(f.getFile());
                            wb.write(fileOut);
                            fileOut.close();
                            wb.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }).to("jpa://gt.com.ad.data.Adsfile?resultClass=gt.com.ad.data.Adsfile&nativeQuery=update repository set step = 3, processed = 1 where step = 1");
    }

}
