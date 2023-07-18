package com.aakhya.smartcall.application.security.service;

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
				for (UserRole userRole : user.getUserRoles()) {
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
						for (UserRole userRole : user.getUserRoles()) {
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
						for (UserRole userRole : user.getUserRoles()) {
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
						for (UserRole userRole : user.getUserRoles()) {
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
						for (UserRole userRole : user.getUserRoles()) {
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
						for (UserRole userRole : user.getUserRoles()) {
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
					for (UserRole userRole : user.getUserRoles()) {
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
							System.out.println("Exisiting user role is :: "+exisitngUserRole.getRoleDescription());
							if (!user.getUserRoles().contains(exisitngUserRole)) {
								System.out.println("User role being deleted is :: "+exisitngUserRole.getRoleDescription());
								rolesRemoved.add(exisitngUserRole);
								userRoleRepository.delete(exisitngUserRole);
							}
						}
					}
					for (UserRole userRole : user.getUserRoles()) {
						System.out.println("New user role is :: "+userRole.getRoleDescription());
						userRole.setCompanyId(user.getCompanyId());
						userRole.setStatus(RecordStatusType.ACTIVE.getValue());
						userRole.setValidFrom(user.getValidFrom());
						userRole.setValidTo(user.getValidTo());
					}
				}
				if(null != user.getUserRoles())
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

	public User generateOtpAndSendSms(User user) throws Exception {
		if (null != user && null != user.getUserId()) {
			Random rnd = new Random();
			Integer number = rnd.nextInt(999999);
			user.setOtpCode(number);
			userRepository.save(user);
			return user;
		} else
			throw new Exception("Invalid User details");
	}

	public User validateOtp(User user) throws Exception {
		if (null != user && null != user.getUserId()) {
			Optional<User> optionalUser = userRepository.findById(user.getUserId());
			if (optionalUser.isPresent()) {
				User userFromDb = optionalUser.get();
				if (user.getOtpCode().equals(userFromDb.getOtpCode()))
					return userFromDb;
				else
					throw new Exception("OTP Validation Failed");
			} else
				throw new Exception("OTP Validation Failed");
		} else
			throw new Exception("OTP Validation Failed");
	}

	public List<User> findUsersByRole(@Param("roleId") Long roleId) {
		List<User> users = userRepository.findUsersByRole(roleId);
		if (null != users && !users.isEmpty()) {
			for (User user : users) {
				if (null != user.getUserRoles() && !user.getUserRoles().isEmpty()) {
					for (UserRole userRole : user.getUserRoles()) {
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
}
