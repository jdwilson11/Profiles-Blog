package org.jdw.blog;

import org.jdw.blog.SenderMain;
import org.jdw.blog.config.Profiles;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@ActiveProfiles(Profiles.TEST)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SenderMain.class)
@WebAppConfiguration
public abstract class BaseSpringJUnitTest {

}
