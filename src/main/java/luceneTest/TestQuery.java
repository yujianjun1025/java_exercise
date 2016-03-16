package luceneTest;

/**
 * Created by yjj on 15/9/24.
 */

import com.google.common.base.Joiner;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

public class TestQuery {
    public static void main(String[] args) throws IOException, ParseException {
        String index = "/Users/yjj/q/code/MulitpleThreadShare/src/main/java/luceneTest/index";         //搜索的索引路径
        IndexReader reader = IndexReader.open(FSDirectory.open(new File(index)));
        IndexSearcher searcher = new IndexSearcher(reader);

        ScoreDoc[] hits = null;
        String queryString = "绝对秋香";   //搜索的关键词
        Query query = null;


        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
        try {
            QueryParser qp = new QueryParser(Version.LUCENE_36, "body", analyzer);
            query = qp.parse(queryString);
        } catch (ParseException e) {
        }
        if (searcher != null) {
            TopDocs results = searcher.search(query, 10);    //返回最多为10条记录

            System.out.println(ToStringBuilder.reflectionToString(results));
            hits = results.scoreDocs;
            if (hits.length > 0) {
                System.out.println(Joiner.on("\n").join(hits));
            }
            searcher.close();
        }

    }

}