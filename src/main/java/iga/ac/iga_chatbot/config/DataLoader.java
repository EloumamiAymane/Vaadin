package iga.ac.iga_chatbot.config;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.io.File;
import java.nio.file.Path;
import java.util.List;

@Component
public class DataLoader {
    @Value("classpath:/pdfs/CV_Aymane.pdf")
    private Resource pdfFile;
    @Value("iga-vs4.json")
    private String  vectorStoreName;
//    private JdbcClient jdbcClient;
//    private VectorStore vectorStore;
//    public DataLoader(JdbcClient  jdbcClient,VectorStore vectorStore){
//        this.jdbcClient=jdbcClient;
//        this.vectorStore=vectorStore;
//    }

   @Bean
    public SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel){
    SimpleVectorStore simpleVectorStore=new SimpleVectorStore(embeddingModel);
    String path= Path.of("src","main","resources", "store").toAbsolutePath()+"/"+vectorStoreName;
    File file= new File(path);
    if(!file.exists()){
        PagePdfDocumentReader pagePdfDocumentReader=new PagePdfDocumentReader(pdfFile);
        List<Document> documents = pagePdfDocumentReader.get();
        TextSplitter textSplitter= new TokenTextSplitter();
        List<Document>chunks=textSplitter.split(documents);
        simpleVectorStore.add(chunks);
        simpleVectorStore.save(file);

    }else{
        System.out.println("exists !");
        simpleVectorStore.load(file);
        }
      return  simpleVectorStore;
    }
//    @PostConstruct
//    public void init(){
//        Integer count=jdbcClient.sql("select count (*) from vector_store")
//                .query(Integer.class).single();
//        if(count ==0){
//            PagePdfDocumentReader pagePdfDocumentReader=new PagePdfDocumentReader(pdfFile);
//            List<Document> documents = pagePdfDocumentReader.get();
//            TextSplitter textSplitter= new TokenTextSplitter();
//            List<Document>chunks=textSplitter.split(documents);
//            vectorStore.add(chunks);
//        }
//
//    }
    }

