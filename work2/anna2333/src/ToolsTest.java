import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class ToolsTest {
    //对WriteCompetition方法的测试
    @Test
    public void Toolstest() {
        try {
            ArrayList<String> list = new ArrayList<>();
            list=Tools.WriteCompetition("output.txt","men 3m springboard");
            Assert.assertArrayEquals("wrong", "[{J17}, {G26}, {A35}, {N46}, {V56}, {N66}, {A76}, {A85}, {K95}, {J16}, {M16}, {L15}]".getBytes(), list.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            ArrayList<String> list = new ArrayList<>();
            list=Tools.WriteCompetition("output.txt","men 1m springboard");
            System.out.println(list);
            Assert.assertArrayEquals("wrong", "[{M15}, {J25}, {T35}, {B45}, {S56}, {G65}, {D75}, {T86}, {I94}, {W14}]".getBytes(), list.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            ArrayList<String> list = new ArrayList<>();
            list=Tools.WriteCompetition("output.txt","women 1m springboard");
            System.out.println(list);
            Assert.assertArrayEquals("wrong", "[{J15}, {A24}, {K35}, {D44}, {A55}, {A64}, {A75}, {S84}, {Y94}, {T14}, {T13}, {D14}]".getBytes(), list.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    //对reverseName方法的测试
    @Test
    public void ToolstestreverseName() {
        Assert.assertEquals("messy lion", Tools.reverseName("lion messy"));
        Assert.assertEquals("doe john", Tools.reverseName("john    doe"));
        Assert.assertEquals("bob alice", Tools.reverseName("alice    bob"));
        Assert.assertEquals("wilson harry & evans hermione", Tools.reverseName("harry wilson & hermione    evans"));
        Assert.assertEquals("potter ginny", Tools.reverseName("ginny    potter"));
        Assert.assertEquals("malfoy draco & weasley ron", Tools.reverseName("draco malfoy & ron    weasley"));
    }


    //对check方法的测试
    @Test
    public void ToolsTest() {
        Assert.assertEquals("wrong",false,Tools.check("fsdfgsdbgsdf") );
        Assert.assertEquals("wrong",false,Tools.check("fzu6657") );
        Assert.assertEquals("wrong",true,Tools.check("men 1m springboard") );
        Assert.assertEquals("wrong",true,Tools.check("men 3m springboard") );
    }

    //对writeCompetitionDetails的测试
    @Test
    public void ToolsTestwriteCompetitionDetails() {
        try {
            ArrayList<String> list = new ArrayList<>();
            list=Tools.writeCompetitionDetails("output.txt","men 3m springboard");
            System.out.println(list);
            Assert.assertArrayEquals("wrong", ("[{667111}, {666261}, {565443}, {466554}, {666792}, {655929}, {656117}, {666175}, " +
                    "{666111}, {57*81*}, {566186}, {545118}, {56*61*}, {45*11*}, {455131}, {35*21*}, {54*11*}, " +
                    "{44*21*}, {6**3**}, {6**1**}, {5**1**}]").getBytes(), list.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
