package com.metagurukul.metaboard.unittest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(LogoutActionTestCase.class);
		suite.addTestSuite(RegistrationActionTestCase.class);
		suite.addTestSuite(RemoveActionTestCase.class);
		suite.addTestSuite(PostActionTestCase.class);
		suite.addTestSuite(TestCaseGroupDAO.class);
		suite.addTestSuite(DeleteActionTestCase.class);
		suite.addTestSuite(ShowActionTestCase.class);
		suite.addTestSuite(TestCaseSectionDAO.class);
		suite.addTestSuite(TestCaseNotificationDAO.class);
		suite.addTestSuite(TestCaseMemberDAO.class);
		suite.addTestSuite(ShowPublicNotificationTestCase.class);
		suite.addTestSuite(UpdateActionTestCase.class);
		//$JUnit-END$
		return suite;
	}

}
