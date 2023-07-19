package com.aakhya.smartcall.application.security.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aakhya.smartcall.application.admin.entity.RecordStatusType;
import com.aakhya.smartcall.application.security.entity.User;
import com.aakhya.smartcall.application.security.entity.UserRole;
import com.aakhya.smartcall.application.security.repository.UserRepository;
import com.aakhya.smartcall.application.security.repository.UserRoleRepository;
import com.aakhyatech.smartcall.application.utils.StringUtils;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;

	public User findUserById(String userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			if (null != user.getUserRoles() && !user.getUserRoles().isEmpty()) {
				for (@SuppressWarnings("unused") UserRole userRole : user.getUserRoles()) {
//					System.out.println(userRole.getRoleDescription());
				}
			}
			return user;
		} else
			return null;
	}

	public List<User> findAllUsers(String userNameFilter, String branchCode) {
		if ((null == userNameFilter || userNameFilter.isEmpty()) && (null == branchCode || branchCode.isEmpty())) {
			List<User> users = userRepository.findAllUsers();
			if (null != users && !users.isEmpty()) {
				for (User user : users) {
					if (null != user.getUserRoles() && !user.getUserRoles().isEmpty()) {
						for (@SuppressWarnings("unused") UserRole userRole : user.getUserRoles()) {
//							System.out.println(userRole.getRoleDescription());
						}
					}
				}
			}
			return users;
		} else if ((null != userNameFilter && !userNameFilter.isEmpty())
				&& (null == branchCode || branchCode.isEmpty())) {
			List<User> users = userRepository.search(userNameFilter);
			if (null != users && !users.isEmpty()) {
				for (User user : users) {
					if (null != user.getUserRoles() && !user.getUserRoles().isEmpty()) {
						for (@SuppressWarnings("unused") UserRole userRole : user.getUserRoles()) {
//							System.out.println(userRole.getRoleDescription());
						}
					}
				}
			}
			return users;
		} else if ((null == userNameFilter || userNameFilter.isEmpty())
				&& (null != branchCode && !branchCode.isEmpty())) {
			System.out.println("Executing branch filter in Service");
			List<User> users = userRepository.searchByBranch(branchCode);
			if (null != users && !users.isEmpty()) {
				for (User user : users) {
					if (null != user.getUserRoles() && !user.getUserRoles().isEmpty()) {
						for (@SuppressWarnings("unused") UserRole userRole : user.getUserRoles()) {
//							System.out.println(userRole.getRoleDescription());
						}
					}
				}
			}
			return users;
		} else if ((null != userNameFilter && !userNameFilter.isEmpty())
				&& (null != branchCode && !branchCode.isEmpty())) {
			List<User> users = userRepository.searchByBranchAndName(userNameFilter, branchCode);
			if (null != users && !users.isEmpty()) {
				for (User user : users) {
					if (null != user.getUserRoles() && !user.getUserRoles().isEmpty()) {
						for (@SuppressWarnings("unused") UserRole userRole : user.getUserRoles()) {
//							System.out.println(userRole.getRoleDescription());
						}
					}
				}
			}
			return users;
		} else {
			List<User> users = userRepository.search(userNameFilter);
			if (null != users && !users.isEmpty()) {
				for (User user : users) {
					if (null != user.getUserRoles() && !user.getUserRoles().isEmpty()) {
						for (@SuppressWarnings("unused") UserRole userRole : user.getUserRoles()) {
//							System.out.println(userRole.getRoleDescription());
						}
					}
				}
			}
			return users;
		}
	}

	public List<User> findUserByBranchCode(String branchCode) {
		List<User> users = userRepository.findUsersByBranch(branchCode);
		if (null != users && !users.isEmpty()) {
			for (User user : users) {
				if (null != user.getUserRoles() && !user.getUserRoles().isEmpty()) {
					for (@SuppressWarnings("unused") UserRole userRole : user.getUserRoles()) {
//						System.out.println(userRole.getRoleDescription());
					}
				}
			}
		}
		return users;
	}

	public void save(User user) {
		if (null != user && null != user.getUserId() && !user.getUserId().isEmpty()) {
			user.setCompanyId(1L);
			user.setStatus(RecordStatusType.ACTIVE.getValue());
			user.setRoleId(1L);
			String password = user.getPassword();
			try {
				String encryptedPassword = new BCryptPasswordEncoder().encode(password);
				String encryptedMobilePassword = StringUtils.encrypt(password);
				user.setPassword(encryptedPassword);
				user.setMobileAppPwd(encryptedMobilePassword);
				List<UserRole> rolesRemoved = new ArrayList<UserRole>();
				if (null != user.getUserRoles() && !user.getUserRoles().isEmpty()) {
					List<UserRole> exisitngUserRoles = userRoleRepository.findExistingRolesForUser(user.getUserId());
					if (null != exisitngUserRoles && !exisitngUserRoles.isEmpty()) {
						for (UserRole exisitngUserRole : exisitngUserRoles) {
							System.out.println("Exisiting user role is :: " + exisitngUserRole.getRoleDescription());
							if (!user.getUserRoles().contains(exisitngUserRole)) {
								System.out.println(
										"User role being deleted is :: " + exisitngUserRole.getRoleDescription());
								rolesRemoved.add(exisitngUserRole);
								userRoleRepository.delete(exisitngUserRole);
							}
						}
					}
					for (UserRole userRole : user.getUserRoles()) {
						System.out.println("New user role is :: " + userRole.getRoleDescription());
						userRole.setCompanyId(user.getCompanyId());
						userRole.setStatus(RecordStatusType.ACTIVE.getValue());
						userRole.setValidFrom(user.getValidFrom());
						userRole.setValidTo(user.getValidTo());
					}
				}
				if (null != user.getUserRoles())
					user.getUserRoles().removeAll(rolesRemoved);
				userRepository.save(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void delete(User user) {
		if (null != user) {
			user.setStatus(RecordStatusType.DELETED.getValue());
			userRepository.save(user);
		}
	}

	public List<User> findUsersByRole(@Param("roleId") Long roleId) {
		List<User> users = userRepository.findUsersByRole(roleId);
		if (null != users && !users.isEmpty()) {
			for (User user : users) {
				if (null != user.getUserRoles() && !user.getUserRoles().isEmpty()) {
					for (@SuppressWarnings("unused")
					UserRole userRole : user.getUserRoles()) {
//						System.out.println(userRole.getRoleDescription());
					}
				}
			}
		}
		return users;
	}

	public void deleteUsers(Set<User> users) {
		for (User user : users) {
			user.setStatus(RecordStatusType.DELETED.getValue());
			userRepository.save(user);
		}
	}

	/*
	 * Following methods are for Mobile backend services
	 */

	public User generateOtpAndSendSms(User user) {
		if (null != user && null != user.getUserId()) {
			System.out.println("The user id from UI is :: " + user.getUserId());
			Optional<User> optionalUser = userRepository.findById(user.getUserId());
			if (null != optionalUser && optionalUser.isPresent()) {
				System.out.println("***** Was able to fetch data from DB");
				User userFromDB = optionalUser.get();
				if (null != userFromDB.getOtpCode() && userFromDB.getOtpCode() > 0) {
					userFromDB.setAuthenticationResult("ALREADY REGISTERED");
					return userFromDB;
				}
				Integer otpCode = getRandomNumber();
				System.out.println("The otp for the user is :: " + otpCode);
				userFromDB.setOtpCode(otpCode);
				sendOtpSms(userFromDB.getUserName(), otpCode, userFromDB.getMobileNumber());
				userRepository.save(userFromDB);
				return userFromDB;
			} else {
				user.setAuthenticationResult("INVALID USERID");
				return user;
			}
		} else
			return null;
	}

	private void sendOtpSms(String userName, Integer otp, String mobileNumber) {
		try {
			StringBuffer urlString = new StringBuffer();
			// urlString.append("http://103.16.101.52:8080/sendsms/bulksms?username=kap2-kapuser&password=trans321&type=0&dlr=1");
			urlString.append(
					"http://173.45.76.227/sendunicode.aspx?username=smartcall&pass=smartcall56&route=trans1&senderid=DANCEE");
			urlString.append("&numbers=" + mobileNumber);
//			urlString.append("&templateid=1207162374774514861");
			urlString.append("&ispreapproved=1");
			urlString.append("&message=");
			StringBuffer message = new StringBuffer("Dear user your verification code is " + otp + " CSPL");
			if (null != message) {
				urlString.append(URLEncoder.encode(message.toString(), "UTF-8"));
				System.out.println(urlString.toString());
				URL url = new URL(urlString.toString());
				HttpURLConnection http = (HttpURLConnection) url.openConnection();
				http.setDoOutput(true);
				http.setRequestMethod("GET");
				http.disconnect();
				BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
				String inputLine;
				if ((inputLine = in.readLine()) != null) {
					if (null != inputLine && inputLine.length() >= 2 && inputLine.startsWith("1|")) {
						// dcbSmsAccount.setSmsStatus("SUCCESS");
					} else
						// dcbSmsAccount.setSmsStatus("FAILED");
						in.close();
				} else {
					// dcbSmsAccount.setSmsStatus("FAILED");
					in.close();
					// return "-1";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Integer getRandomNumber() {
		Random rnd = new Random();
		Integer number = rnd.nextInt(9999);
		if (number <= 999)
			return getRandomNumber();
		else
			return number;
	}

	public String validateOtp(User user) throws Exception {
		if (null != user && null != user.getUserId()) {
			Optional<User> optionalUser = userRepository.findById(user.getUserId());
			if (optionalUser.isPresent()) {
				User userFromDb = optionalUser.get();
				if (user.getOtpCode().equals(userFromDb.getOtpCode()))
					return "SUCCESS";
				else
					throw new Exception("OTP Validation Failed");
			} else
				return "FAILED";
//				throw new Exception("OTP Validation Failed");
		} else
			return "FAILED";
//			throw new Exception("OTP Validation Failed");
	}

	public User authenticateUser(User user) {
		try {
			Optional<User> optionalUser = userRepository.findById(user.getUserId());
			if (null != optionalUser && optionalUser.isPresent()) {
				User userFromDB = optionalUser.get();
				String pwdFromUi = user.getPassword();
				String pwdFromUiEncrypted;
				try {
					pwdFromUiEncrypted = StringUtils.encrypt(pwdFromUi);
					if (pwdFromUiEncrypted.equals(userFromDB.getMobileAppPwd()))
						userFromDB.setAuthenticationResult("SUCCESS");
					else
						userFromDB.setAuthenticationResult("INVALID PASSWORD");
					return userFromDB;
				} catch (Exception e) {
					userFromDB.setAuthenticationResult("INVALID PASSWORD");
					return userFromDB;
				}

			} else {
				user.setAuthenticationResult("INVALID USERID");
				return user;
			}
		} catch (Exception e) {
			user.setAuthenticationResult("INVALID USERID");
			return user;
		}
	}

	public User resetPassword(User user) {
		if (null != user && null != user.getUserId() && !user.getUserId().isEmpty()) {
			try {
				Optional<User> os = userRepository.findById(user.getUserId());
				if (null != os && !os.isPresent()) {
					User userFromDb = os.get();
					String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
					String encryptedMobilePassword = StringUtils.encrypt(user.getPassword());
					userFromDb.setPassword(encryptedPassword);
					userFromDb.setMobileAppPwd(encryptedMobilePassword);
					userRepository.save(userFromDb);
					return userFromDb;
				} else
					return null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
