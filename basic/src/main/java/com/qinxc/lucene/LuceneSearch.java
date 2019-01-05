package com.qinxc.lucene;

import org.apache.lucene.index.SegmentInfos;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.similarities.Similarity;

import static org.apache.lucene.search.BooleanClause.Occur.SHOULD;

/**
 * @author qxc
 * @date 2018/8/31.
 */
public class LuceneSearch {

    public static void main(String[] args) {

        Similarity similarity;
        SegmentInfos segmentInfos;

        //****QueryClasses
        //1 TermQuery
        TermQuery tq = new TermQuery(new Term("fieldName", "term"));

        //BooleanQuery
        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        builder.add(new TermQuery(new Term("fieldName", "term1")), SHOULD);
        builder.add(new TermQuery(new Term("fieldName", "term2")), SHOULD);


        //2 Phrase
        //PhraseQuuery
        //匹配短语 规定短语相对位置 只计算position相对位置 下面4 5是一样的效果
        PhraseQuery.Builder pbuilder = new PhraseQuery.Builder();
        pbuilder.add(new Term("fieldName", "termValue"), 0);
        pbuilder.add(new Term("fieldName", "termValue"), 1);

        //MultiPhraseQuery
        //SpanNearQuery

        //3 TermRangeQuery
        //PointRangeQuery ...


        //****Scoring




    }

}
