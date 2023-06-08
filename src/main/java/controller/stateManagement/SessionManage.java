package controller.stateManagement;

public class SessionManage {
	public Boolean checkUser(String userSession) {
		if(userSession != null && !userSession.isEmpty()) return true;
		else return false;
	}
	
	public Boolean checkIsAdmin(String userSession) {
		if(userSession != null && !userSession.isEmpty() && userSession.equals("admin@admin.com")) {
			return true;
		}else {
			return false;
		}
	}
	
}

//package controller.stateManagement;
//
//public class SessionManage {
//	public Boolean checkUser(String userSession, boolean isAdmin) {
//		if(isAdmin) {
//		if(userSession != null && !userSession.isEmpty() && userSession.equals("admin")) {
//			return true;
//		}else {
//			return false;
//		}
//		}else {
//		
//		//check if the session attribute "user" is empty or null
//			if(userSession != null && !userSession.isEmpty()) {
//				return true;
//			}else {
//				return false;
//			}
//			
//		}
//		
//	}
//}
