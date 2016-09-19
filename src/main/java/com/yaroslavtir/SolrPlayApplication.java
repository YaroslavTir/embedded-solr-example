package com.yaroslavtir;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.core.CoreContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication
@ComponentScan
public class SolrPlayApplication {
    public static void main(String[] args) {
        SpringApplication.run(SolrPlayApplication.class, args);
    }


    @Bean
    public SolrServer getSolrServer() {
        CoreContainer  container = new CoreContainer("testdata/solr");
        container.load();
        EmbeddedSolrServer server = new EmbeddedSolrServer(container, "collection1");

        try {
            server.deleteByQuery("*:*");
            server.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }

        SolrInputDocument document = new SolrInputDocument();
        document.addField("name", "bob");
        document.addField("text", "Lorem ipsum");
        document.addField("id", "" + System.currentTimeMillis());

        try {
            server.add(document);
            server.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        return server;
    }

}
