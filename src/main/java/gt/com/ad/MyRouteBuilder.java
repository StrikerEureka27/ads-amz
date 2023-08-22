package gt.com.ad;

import java.io.File;
import java.io.FileOutputStream;
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
import gt.com.ad.data.KrnRepository;

import java.io.ByteArrayOutputStream;


public class MyRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("jpa://gt.com.ad.data.KrnRepository?consumeDelete=false&nativeQuery=select * from krn_repository where step = 1")
                .routeId("xlsx")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        KrnRepository f = exchange.getIn().getBody(KrnRepository.class);
                        File tmp = File.createTempFile(f.getName(), ".xlsx");
                        FileOutputStream fos = new FileOutputStream(tmp);

                        try {
                            fos.write(f.getFile());
                            tmp.delete();
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
                        String refBid = "";

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

                                if (ref.formatAsString().substring(0, 2).equals("AB")) {
                                    refBid = ref.formatAsString().toString();
                                }

                                if (ref.formatAsString().substring(0, 1).equals("C") && row.getRowNum()!=0) {
                                    cell.setBlank();
                                    cell.setCellValue("Update");
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
                                    //log.info(String.format("row index %d res : %f", row.getRowNum(), res));
                                    if (res <= 0.067) {
                                        filterStepFive.add(Integer.valueOf(row.getRowNum()));
                                    }
                                }
                            }

                            if (row.getRowNum() == 0) {
                                row.createCell(row.getLastCellNum(),CellType.STRING).setCellValue("CVR");
                            } else {
                                row.createCell(row.getLastCellNum() , CellType.FORMULA).setCellFormula(String.format("%s/%s", refOrder, refClick));
                            }

                            if (row.getRowNum() == 0) {
                                row.createCell(row.getLastCellNum() ,CellType.STRING).setCellValue("Previous Bid");
                            } else {
                                row.createCell(row.getLastCellNum(), CellType.FORMULA).setCellFormula(String.format("%s", refBid));
                            }

                            if (row.getRowNum() == 0) {
                                row.createCell(row.getLastCellNum(),CellType.STRING).setCellValue("New Bid");
                            } else {
                                row.createCell(row.getLastCellNum(), CellType.FORMULA).setCellFormula(String.format("%s*1.20", refBid));
                            }

                            if (row.getRowNum() == 0) {
                                row.createCell(row.getLastCellNum(),CellType.STRING).setCellValue("Bid Change");
                            } else {
                                row.createCell(row.getLastCellNum(), CellType.FORMULA).setCellFormula(String.format("((%s*1.20)-%s)/%s", refBid, refBid, refBid));
                            }

                        }

                        for (Integer r : filterStepOne) {
                            Row row = sheet1.getRow(r);
                            if (row != null) {
                                sheet1.removeRow(row);
                            }
                        }

                        for (Integer r : filterStepTwo) {
                            Row row = sheet1.getRow(r);
                            if (row != null) {
                                sheet1.removeRow(row);
                            }
                        }

                        for (Integer r : filterStepTree) {
                            Row row = sheet1.getRow(r);
                            if (row != null) {
                                sheet1.removeRow(row);
                            }
                        }

                        for (Integer r : filterStepFour) {
                            Row row = sheet1.getRow(r);
                            if (row != null) {
                                sheet1.removeRow(row);
                            }
                        }

                        for (Integer r : filterStepFive) {
                            Row row = sheet1.getRow(r);
                            if (row != null) {
                                sheet1.removeRow(row);
                            }
                        }

                        // Saving workbook
                        try {
                            ByteArrayOutputStream executionTmpFile = new ByteArrayOutputStream();
                            wb.write(executionTmpFile);
                            f.setFile(executionTmpFile.toByteArray());
                            f.setProcessed(true);
                            f.setStep(2);
                            String header = String.format("File %d processed successfully.", f.getId());
                            exchange.getIn().setHeader("log", header);
                            executionTmpFile.close();
                            wb.close();
                        } catch (Exception e) {
                            exchange.getIn().setHeader("log","Something go wrong processing file.");
                            e.printStackTrace();
                        }

                    }
                })
                .toD("jpa://gt.com.ad.data.AdsLog?resultClass=gt.com.ad.data.AdsLog&nativeQuery=insert into log (message) values ('${header.log}')");
    }

}
