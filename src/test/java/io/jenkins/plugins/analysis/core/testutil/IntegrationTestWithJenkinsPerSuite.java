package io.jenkins.plugins.analysis.core.testutil;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.JenkinsRule.WebClient;

/**
 * Base class for integration tests in Jenkins. Sub classes will get a new and fresh Jenkins instance for each test
 * case.
 *
 * @author Ullrich Hafner
 */
public abstract class IntegrationTestWithJenkinsPerSuite extends IntegrationTest {
    /** Jenkins rule per suite. */
    @ClassRule
    public static final JenkinsRule JENKINS_PER_SUITE = new JenkinsRule();

    @Override
    protected JenkinsRule getJenkins() {
        return JENKINS_PER_SUITE;
    }

    private static WebClient noJsWebClient;
    private static WebClient jsEnabledClient;

    @BeforeClass
    public static void createWebClients() {
        noJsWebClient = create(false);
        jsEnabledClient = create(true);
    }

    @Override
    protected WebClient getWebClient(final JsSupport javaScriptSupport) {
        return javaScriptSupport == JsSupport.NO_JS ? noJsWebClient : jsEnabledClient;
    }
}
