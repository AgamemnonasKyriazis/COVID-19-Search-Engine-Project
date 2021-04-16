package indexsearcher;


import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

public class _IndexSearcher {

	public String searchIndex(IndexReader reader, String term) throws IOException, ParseException {
		IndexSearcher searcher = new IndexSearcher(reader);
		QueryParser qp = new QueryParser("contents", new StandardAnalyzer());
		Query query = qp.parse(term);
		TopDocs docs = searcher.search(query, 10);
		ScoreDoc[] hits = docs.scoreDocs;
		System.out.println("Found " + hits.length + " hits.");
		String ret = "";
        for(int i=0; i < hits.length; i++) {
        	int docId = hits[i].doc;
        	Document d = searcher.doc(docId);
        	ret += ((i + 1) + ". " + d.get("title")) + '\n';
        }
        return ret;
 }
}
