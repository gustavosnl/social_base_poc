package com.glima.socialbasepoc.network.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glima.socialbasepoc.model.Images;
import com.glima.socialbasepoc.model.Show;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 05/07/16.
 */
public class ShowParser {

    public List<Show> parse(InputStream inputStream) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(inputStream);

        List<Show> shows = new ArrayList<>();
        for (JsonNode showNode : rootNode) {
            Show show = new Show(
                    showNode.at("/ids/trakt").asText(),
                    showNode.at("/title").asText(),
                    showNode.at("/year").asText(),
                    showNode.at("/overview").asText(),
                    showNode.at("/aired_episodes").asInt(),
                    getImages(showNode.at("/images")));
            shows.add(show);
        }
        return shows;
    }


    private Images getImages(JsonNode imagesNode) {
        return new Images(
                imagesNode.at("/thumb/full").asText(),
                imagesNode.at("/clearart/full").asText());
    }
}
