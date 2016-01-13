package synonmnTest;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.io.IOException;
import java.util.List;

/**
 * Created by yjj on 15/12/10.
 */
public class ansjTest {


    public static void main(String[] args) throws IOException {
        String str = "欢迎使用ansj_seg,(ansj中文分词)在这里1234567891yujianjun student sw如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!" ;

        List<Term> terms = ToAnalysis.parse(str);

        for(Term term : terms){
            System.out.println(term.getName());
        }
        //System.out.println(ToAnalysis.parse(str));
    }

}
