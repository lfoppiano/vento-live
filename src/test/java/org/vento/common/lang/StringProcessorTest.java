package org.vento.common.lang;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

public class StringProcessorTest {

    @Test
    public void testTextNormalization1() throws Exception {
        final String input = "\"The Future of Google Maps: You'll Never Be Lost Again\": http://t.co/xXYRSrSi";
        String output = "\"The Future of Google Maps: You'll Never Be Lost Again\": _LINK_";
        assertThat(StringProcessor.normalize(input), is(output));

    }

    @Test
    public void testTextNormalization2() throws Exception {
        final String input = "@lddio c'è un certo #berlusconi che dopo la recente performance a #serviziopubblico vorrebbe confrontarsi con te...";
        String output = "_USER_ c'è un certo _TAG_ che dopo la recente performance a _TAG_ vorrebbe confrontarsi con te...";
        assertThat(StringProcessor.normalize(input), is(output));
    }

    @Test
    public void testTextNormalization3() throws Exception {

        final String input = "RT: IL FATTO: Nadia Macrì: Berlusconi a letto ha grandi capacità, mi manca tantissimo. NO COMMENT!! http://t.co/H1nHraC8";
        String output = "_RETWEET_ IL FATTO: Nadia Macrì: Berlusconi a letto ha grandi capacità, mi manca tantissimo. NO COMMENT!! _LINK_";
        assertThat(StringProcessor.normalize(input), is(output));

    }

    @Test
    public void testTextNormalization4() throws Exception {
            final String input = "#russia's slide toward economic crisis - and why it matters #news http://t.co/ahvR42rkpY #business #market";
        String output = "_TAG_'s slide toward economic crisis - and why it matters _TAG_ _LINK_ _TAG_ _TAG_";
        assertThat(StringProcessor.normalize(input), is(output));

    }


}
