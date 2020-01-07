package de.hk.boundary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;


@Provider
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class JacksonProducer implements ContextResolver<ObjectMapper> {
    public JacksonProducer() throws Exception {
        this.objectMapper = new ObjectMapper().findAndRegisterModules();
        objectMapper.registerModule(new JSR310Module());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
    @Override
    public ObjectMapper getContext( Class<?> objectType ) {
        return objectMapper;
    }

    private final ObjectMapper objectMapper;
}