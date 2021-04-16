package mainengine;
import java.io.IOException;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;

import indexreader._IndexReader;
import indexsearcher._IndexSearcher;
import indexwriter._IndexWriter;

public class MainEngine {
	
	private IndexReader reader;
	
	public MainEngine() {
		reader = null;
	}
	
	public int writeIndex(String indexPath, String dataPath) {
		_IndexWriter indexWriter = new _IndexWriter(indexPath, dataPath);
		try {
			indexWriter.writeIndex();
		} catch (IOException e) {
			return 2;
		}
		return 0;
	}
	
	public int readIndex(String indexPath) {
		_IndexReader indexReader = new _IndexReader(indexPath);
		try {
			reader = indexReader.readIndex();
		} catch (IOException e) {
			return 2;
		} catch (ParseException e) {
			return 3;
		}
		return 0;
	}
	
	public String searchIndex(String term) {
		_IndexSearcher indexSearcher = new _IndexSearcher();
		String ret = null;
		try {
			ret = indexSearcher.searchIndex(reader, term);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
