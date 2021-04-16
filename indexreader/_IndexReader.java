package indexreader;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class _IndexReader {
	
	 File indexDir;
	 
	 public _IndexReader(String indexPath) {
		 indexDir = new File(indexPath);
	}

	public IndexReader readIndex() throws IOException, ParseException {
		 Directory directory = FSDirectory.open(indexDir.toPath());
		 IndexReader reader = DirectoryReader.open(directory);
		 directory.close();
		 return reader;
		}
	 
}
