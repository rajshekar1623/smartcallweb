package com.aakhya.smartcall.application.integration.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import com.aakhya.smartcall.application.integration.entity.IntegrationMaster;
import com.aakhya.smartcall.application.transaction.data.entity.TemporaryTransaction;
import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;
import com.aakhya.smartcall.application.transaction.data.service.TransactionDataSetService;

public class ExcelFileReader implements FileReader {

	private static final SimpleDateFormat df = new SimpleDateFormat("dd-mm-yy");

	@Override
	public boolean checkFile(IntegrationMaster im, List<String> linesFromFile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TransactionDataSet> processFile(IntegrationMaster im, List<String> linesFromFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "unused", "resource", "incomplete-switch" })
	public static List<TransactionDataSet> processFile(FileInputStream fis) {
		List<TransactionDataSet> transactionDataSets = new ArrayList<TransactionDataSet>();
		try {
//			FileInputStream fis = new FileInputStream(new File("NPATRACKER_13062023 (002).xls"));
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			HSSFSheet sheet = wb.getSheetAt(0);
			DataFormatter formatter = new DataFormatter();
			FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
			int rowCount = 0;
			for (Row row : sheet) // iteration over row using for each loop
			{
				TransactionDataSet transactionDataSet = new TransactionDataSet();
				int columnCount = 0;
				if (rowCount > 0) {
					for (Cell cell : row) // iteration over cell using for each loop
					{
						Object obj = new Object();
						if (cell.getCellType() == CellType.FORMULA) {
							switch (cell.getCachedFormulaResultType()) {
							case NUMERIC:
								obj = cell.getNumericCellValue();
								break;
							case STRING: // field that represents string cell type
								obj = cell.getStringCellValue();
								break;
							}
						} else {
							String text = formatter.formatCellValue(cell);
							obj = text;
						}
						if (columnCount == 0 && null != obj)
							transactionDataSet.setFirstName(obj.toString());
						else if (columnCount == 1 && null != obj) {
							String dateOfBirthStr = obj.toString();
							Date dateOfBirth = new SimpleDateFormat("dd-mm-yy").parse(dateOfBirthStr);
							transactionDataSet.setDateOfBirth(dateOfBirth);
						} else if (columnCount == 2 && null != obj) {
							String genderStr = obj.toString();
							if ("M".equals(genderStr))
								transactionDataSet.setGender(405L);
							else if ("F".equals(genderStr))
								transactionDataSet.setGender(406L);
						} else if (columnCount == 8 && null != obj)
							transactionDataSet.setPanCardNumber(obj.toString());
						else if (columnCount == 9 && null != obj) {
							transactionDataSet.setGenericString5(obj.toString());
						} else if (columnCount == 10 && null != obj) {
							String pincodeStr = obj.toString();
							if (!" ".equals(pincodeStr)) {
								Long pincode = Long.valueOf(pincodeStr);
								transactionDataSet.setGenericNumber4(pincode);
							}
//						} else if (columnCount == 11 && null != obj) {
//							// TODO
						} else if (columnCount == 12 && null != obj) {
							if(null != obj.toString() && obj.toString().length() == 1) {
							String branchCode = "0000" + obj.toString().replace("  ", "").replace("  ", "");
							transactionDataSet.setBranchCode(branchCode);
							}else if(null != obj.toString() && obj.toString().length() == 2) {
								String branchCode = "000" + obj.toString().replace("  ", "").replace("  ", "");
								transactionDataSet.setBranchCode(branchCode);
							}
						} else if (columnCount == 13 && null != obj) {
							transactionDataSet.setGenericString4(obj.toString());
//						} else if (columnCount == 14 && null != obj) {
//							// TODO
						} else if (columnCount == 15 && null != obj) {
							transactionDataSet.setGenericString2(obj.toString());
						} else if (columnCount == 16 && null != obj) {
//							System.out.println(obj);
							Long mobileNumber = Long.valueOf(obj.toString());
							transactionDataSet.setGenericNumber1(mobileNumber);
						} else if (columnCount == 17 && null != obj) {
							Long loanAccountNumber = Long.valueOf(obj.toString());
							transactionDataSet.setGenericNumber12(loanAccountNumber);
						} else if (columnCount == 18 && null != obj) {
							String cabDpdQueue = obj.toString();
							if ("00".equals(cabDpdQueue) || "01".equals(cabDpdQueue))
								transactionDataSet.setGenericNumber13(3738L);
							else if ("02".equals(cabDpdQueue))
								transactionDataSet.setGenericNumber13(3739L);
							else if ("03".equals(cabDpdQueue))
								transactionDataSet.setGenericNumber13(3740L);
							else
								transactionDataSet.setGenericNumber13(3750L);
						} else if (columnCount == 19 && null != obj) {
							BigDecimal currentBalance = new BigDecimal(obj.toString());
							transactionDataSet.setGenericDecimal4(currentBalance);
							transactionDataSet.setGenericDecimal6(currentBalance);
						} else if (columnCount == 21 && null != obj) {
							String interestDueStr = obj.toString();
							if (!" ".equals(interestDueStr)) {
								BigDecimal interestDue = new BigDecimal(obj.toString());
								transactionDataSet.setGenericDecimal5(interestDue);
							} else {
								transactionDataSet.setGenericDecimal5(BigDecimal.ZERO);
							}
						} else if (columnCount == 22 && null != obj) {
							String interstRateStr = obj.toString();
							if (!" ".equals(interstRateStr)) {
								try {
									BigDecimal interestRate = new BigDecimal(obj.toString());
									transactionDataSet.setGenericDecimal5(interestRate);
								} catch (NumberFormatException e) {
									transactionDataSet.setGenericDecimal5(BigDecimal.ZERO);
								}
							} else
								transactionDataSet.setGenericDecimal5(BigDecimal.ZERO);
						} else if (columnCount == 24 && null != obj) {
							String npaDateStr = obj.toString();
							Date npaDate = new SimpleDateFormat("dd-mm-yy").parse(npaDateStr);
							transactionDataSet.setGenericDate1(npaDate);
							transactionDataSet.setGenericDate2(npaDate);
						}
						columnCount++;
					}
					transactionDataSets.add(transactionDataSet);
				}
				rowCount++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactionDataSets;
	}

	public static List<TransactionDataSet> processDilimitedFile(FileInputStream fis,
			Long initialCount,TransactionDataSetService service) {
//		TransactionDataUploadEntity transactionDataUpload = new TransactionDataUploadEntity();
		List<TransactionDataSet> transactionDataSets = new ArrayList<TransactionDataSet>();
		InputStreamReader inputStreamReader = new InputStreamReader(fis);
		BufferedReader reader = new BufferedReader(inputStreamReader);
		try {
			int rowCount = 0;
//			Long updateCount = 0L;
//			Long createCount = 0L;
			while (reader.ready()) {
				String line = reader.readLine();
				if (rowCount > 0) {
					TransactionDataSet dataSet = new TransactionDataSet();
					String[] data = line.split("\\|");
//					System.out.println(data.length);
					if (data.length >= 25) {
						String firstName = data[0];
						if(null != firstName && firstName.replaceAll(" ","").length() > 0) {
						dataSet.setFirstName(firstName);

						String dateOfBirthStr = data[1];
						try {
							Date dateOfBirth = df.parse(dateOfBirthStr);
							dataSet.setDateOfBirth(dateOfBirth);
						} catch (Exception e) {
							dataSet.setDateOfBirth(null);
						}
//
						String genderStr = data[2];
						if (null != genderStr && "M".equals(genderStr))
							dataSet.setGender(405L);
						else if (null != genderStr && "F".equals(genderStr))
							dataSet.setGender(405L);
//
						String panCardNumber = data[8];
						dataSet.setPanCardNumber(panCardNumber);
//
						String address = data[9];
						dataSet.setGenericString5(address);
//
						String pincodeStr = data[10];
						if (null != pincodeStr && pincodeStr.replaceAll(" ", "").length() > 0) {
							try {
								Long pincode = Long.valueOf(pincodeStr.replaceAll(" ", "").replace("`",""));
								dataSet.setGenericNumber4(pincode);
							} catch (Exception e) {
								dataSet.setGenericNumber4(null);
							}
						}
//
						String branchCodeStr = data[12];
						if (null != branchCodeStr)
							System.out.println("branchCodeStr::"+branchCodeStr);
						if (null != branchCodeStr && branchCodeStr.replaceAll(" ", "").length() > 0) {
							String branchCode = "000" + branchCodeStr.replaceAll(" ", "");
							if(branchCode.length() == 4)
								branchCode = "0"+branchCode;
							dataSet.setBranchCode(branchCode);
						}
//
						String branchName = data[13];
						dataSet.setGenericString4(branchName);
//
						String product = data[15];
						dataSet.setGenericString2(product);
//
						String mobileNumberStr = data[16];
						if (null != mobileNumberStr && mobileNumberStr.replaceAll(" ", "").length() > 0) {
							try {
								Long mobileNumber = Long.valueOf(mobileNumberStr);
								dataSet.setGenericNumber1(mobileNumber);
							} catch (Exception e) {
								dataSet.setGenericNumber1(null);
							}
						}
						String loanAccountNumberStr = data[17];
						if (null != loanAccountNumberStr && loanAccountNumberStr.replaceAll(" ", "").length() > 0) {
							try {
								Long loanAccountNumber = Long.valueOf(loanAccountNumberStr);
								dataSet.setGenericNumber2(loanAccountNumber);
//								if(service.checkIfLoanAccountNumberExisit(loanAccountNumber))
//									updateCount++;
//								else
//									createCount++;
							} catch (Exception e) {
								dataSet.setGenericNumber2(null);
							}
						}
						String dpdQueueStr = data[18];
						if(null != dpdQueueStr)
							System.out.println("dpdQueueStr::"+dpdQueueStr);
						if (null != dpdQueueStr && dpdQueueStr.replaceAll(" ", "").length() > 0) {
							if ("00".equals(dpdQueueStr) || "01".equals(dpdQueueStr))
								dataSet.setGenericNumber3(3738L);
							else if ("02".equals(dpdQueueStr))
								dataSet.setGenericNumber3(3739L);
							else if ("03".equals(dpdQueueStr))
								dataSet.setGenericNumber3(3740L);
							else
								dataSet.setGenericNumber3(3750L);
						}else
							dataSet.setGenericNumber3(3738L);

						String currentBalanceStr = data[19];
						if (null != currentBalanceStr && currentBalanceStr.replaceAll(" ", "").length() > 0) {
							try {
								BigDecimal currentBalance = new BigDecimal(currentBalanceStr);
								dataSet.setGenericDecimal4(currentBalance);
								dataSet.setGenericDecimal6(currentBalance);
							} catch (Exception e) {
								dataSet.setGenericDecimal4(null);
								dataSet.setGenericDecimal6(null);
							}
						}
						String interestDueStr = data[21];
						if (null != interestDueStr && interestDueStr.replaceAll(" ", "").length() > 0) {
							try {
								BigDecimal interestDue = new BigDecimal(interestDueStr);
								dataSet.setGenericDecimal5(interestDue);
							} catch (Exception e) {
								dataSet.setGenericDecimal5(null);
							}
						}
						String interestRateStr = data[22];
						if (null != interestDueStr && interestDueStr.replaceAll(" ", "").length() > 0) {
							try {
								BigDecimal interestRate = new BigDecimal(interestRateStr);
								dataSet.setGenericDecimal7(interestRate);
							} catch (Exception e) {
								dataSet.setGenericDecimal7(null);
							}
						}
						String npaDateStr = data[24];
						if (null != interestDueStr && interestDueStr.replaceAll(" ", "").length() > 0) {
							try {
								Date npaDate = df.parse(npaDateStr);
								dataSet.setGenericDate1(npaDate);
								dataSet.setGenericDate2(npaDate);
							} catch (Exception e) {
								Calendar cal = Calendar.getInstance();
							    cal.add(Calendar.MONTH, -1);
							    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
							    Date npaDate = cal.getTime();
							    dataSet.setGenericDate1(npaDate);
								dataSet.setGenericDate2(npaDate);
							}
						}else {
							Calendar cal = Calendar.getInstance();
						    cal.add(Calendar.MONTH, -1);
						    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
						    Date npaDate = cal.getTime();
						    dataSet.setGenericDate1(npaDate);
							dataSet.setGenericDate2(npaDate);
						}
						transactionDataSets.add(dataSet);
					}
				}
				}
				rowCount++;
			}
//			transactionDataUpload.setUploadedData(transactionDataSets);
//			transactionDataUpload.setInitialCount(initialCount);
//			transactionDataUpload.setCreateCount(createCount);
//			transactionDataUpload.setUpdateCount(updateCount);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return transactionDataSets;
	}

	public static List<TemporaryTransaction> processDilimitedFileNew(FileInputStream fis,
			Long initialCount,TransactionDataSetService service) {
//		TransactionDataUploadEntity transactionDataUpload = new TransactionDataUploadEntity();
		List<TemporaryTransaction> temporaryTransactions = new ArrayList<TemporaryTransaction>();
		InputStreamReader inputStreamReader = new InputStreamReader(fis);
		BufferedReader reader = new BufferedReader(inputStreamReader);
		try {
			int rowCount = 0;
//			Long updateCount = 0L;
//			Long createCount = 0L;
			while (reader.ready()) {
				String line = reader.readLine();
				if (rowCount > 0) {
					TemporaryTransaction temporaryTransaction = new TemporaryTransaction();
					String[] data = line.split("\\|");
//					System.out.println(data.length);
					if (data.length >= 25) {
						String firstName = data[0];
						if(null != firstName && firstName.replaceAll(" ","").length() > 0) {
							temporaryTransaction.setFirstname(firstName);

						String dateOfBirthStr = data[1];
						try {
							Date dateOfBirth = df.parse(dateOfBirthStr);
							temporaryTransaction.setDob(dateOfBirth);
						} catch (Exception e) {
							temporaryTransaction.setDob(null);
						}
//
						String genderStr = data[2];
						if (null != genderStr && "M".equals(genderStr))
							temporaryTransaction.setGender(405L);
						else if (null != genderStr && "F".equals(genderStr))
							temporaryTransaction.setGender(405L);
//
						String panCardNumber = data[8];
						temporaryTransaction.setPancard(panCardNumber);
//
						String address = data[9];
						temporaryTransaction.setAddress(address);
//
						String pincodeStr = data[10];
						temporaryTransaction.setPincode(pincodeStr);
//						if (null != pincodeStr && pincodeStr.replaceAll(" ", "").length() > 0) {
//							try {
//								Long pincode = Long.valueOf(pincodeStr.replaceAll(" ", "").replace("`",""));
//								temporaryTransaction.setPincode(pincode);
//							} catch (Exception e) {
//								dataSet.setGenericNumber4(null);
//							}
//						}
//
						String branchCodeStr = data[12];
						if (null != branchCodeStr)
							System.out.println("branchCodeStr::"+branchCodeStr);
						if (null != branchCodeStr && branchCodeStr.replaceAll(" ", "").length() > 0) {
							String branchCode = "000" + branchCodeStr.replaceAll(" ", "");
							if(branchCode.length() == 4)
								branchCode = "0"+branchCode;
							temporaryTransaction.setBranchcode(branchCode);
						}
//
						String branchName = data[13];
						temporaryTransaction.setBranchname(branchName);
//
						String product = data[15];
						temporaryTransaction.setProduct(product);
//
						String mobileNumberStr = data[16];
						temporaryTransaction.setMobilenumber(mobileNumberStr);
//						if (null != mobileNumberStr && mobileNumberStr.replaceAll(" ", "").length() > 0) {
//							try {
//								Long mobileNumber = Long.valueOf(mobileNumberStr);
//								dataSet.setGenericNumber1(mobileNumber);
//							} catch (Exception e) {
//								dataSet.setGenericNumber1(null);
//							}
//						}
						String loanAccountNumberStr = data[17];
						temporaryTransaction.setLoanaccountnumber(loanAccountNumberStr);
//						if (null != loanAccountNumberStr && loanAccountNumberStr.replaceAll(" ", "").length() > 0) {
//							try {
//								Long loanAccountNumber = Long.valueOf(loanAccountNumberStr);
//								dataSet.setGenericNumber2(loanAccountNumber);
////								if(service.checkIfLoanAccountNumberExisit(loanAccountNumber))
////									updateCount++;
////								else
////									createCount++;
//							} catch (Exception e) {
//								dataSet.setGenericNumber2(null);
//							}
//						}
						String dpdQueueStr = data[18];
						if(null != dpdQueueStr)
							System.out.println("dpdQueueStr::"+dpdQueueStr);
						if (null != dpdQueueStr && dpdQueueStr.replaceAll(" ", "").length() > 0) {
							if ("00".equals(dpdQueueStr) || "01".equals(dpdQueueStr))
								temporaryTransaction.setDpdqueue(3738L);
							else if ("02".equals(dpdQueueStr))
								temporaryTransaction.setDpdqueue(3739L);
							else if ("03".equals(dpdQueueStr))
								temporaryTransaction.setDpdqueue(3740L);
							else
								temporaryTransaction.setDpdqueue(3750L);
						}else
							temporaryTransaction.setDpdqueue(3738L);

						String currentBalanceStr = data[19];
						if (null != currentBalanceStr && currentBalanceStr.replaceAll(" ", "").length() > 0) {
							try {
								BigDecimal currentBalance = new BigDecimal(currentBalanceStr);
								temporaryTransaction.setCurrentoutstandingbalance(currentBalance);
//								dataSet.setGenericDecimal6(currentBalance);
							} catch (Exception e) {
								temporaryTransaction.setCurrentoutstandingbalance(null);
//								dataSet.setGenericDecimal6(null);
							}
						}
						String interestDueStr = data[21];
						if (null != interestDueStr && interestDueStr.replaceAll(" ", "").length() > 0) {
							try {
								BigDecimal interestDue = new BigDecimal(interestDueStr);
								temporaryTransaction.setInterestdue(interestDue);
							} catch (Exception e) {
								temporaryTransaction.setInterestdue(null);
							}
						}
						String interestRateStr = data[22];
						if (null != interestDueStr && interestDueStr.replaceAll(" ", "").length() > 0) {
							try {
								BigDecimal interestRate = new BigDecimal(interestRateStr);
								temporaryTransaction.setInterestrate(interestRate);
							} catch (Exception e) {
								temporaryTransaction.setInterestrate(null);
							}
						}
						String npaDateStr = data[24];
						if (null != interestDueStr && interestDueStr.replaceAll(" ", "").length() > 0) {
							try {
								Date npaDate = df.parse(npaDateStr);
								temporaryTransaction.setNpadate(npaDate);
//								dataSet.setGenericDate2(npaDate);
							} catch (Exception e) {
								Calendar cal = Calendar.getInstance();
							    cal.add(Calendar.MONTH, -1);
							    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
							    Date npaDate = cal.getTime();
							    temporaryTransaction.setNpadate(npaDate);
//								dataSet.setGenericDate2(npaDate);
							}
						}else {
							Calendar cal = Calendar.getInstance();
						    cal.add(Calendar.MONTH, -1);
						    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
						    Date npaDate = cal.getTime();
						    temporaryTransaction.setNpadate(npaDate);
//							dataSet.setGenericDate2(npaDate);
						}
						temporaryTransactions.add(temporaryTransaction);
					}
				}
				}
				rowCount++;
			}
//			transactionDataUpload.setUploadedData(transactionDataSets);
//			transactionDataUpload.setInitialCount(initialCount);
//			transactionDataUpload.setCreateCount(createCount);
//			transactionDataUpload.setUpdateCount(updateCount);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return temporaryTransactions;
	}
}
