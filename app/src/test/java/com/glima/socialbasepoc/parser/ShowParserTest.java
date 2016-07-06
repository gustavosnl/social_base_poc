package com.glima.socialbasepoc.parser;

import com.glima.socialbasepoc.model.Images;
import com.glima.socialbasepoc.model.Show;
import com.glima.socialbasepoc.network.parser.ShowParser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by gustavo on 05/07/16.
 */

@RunWith(JUnit4.class)
public class ShowParserTest {

    final String DESCRIPTION = "An anthology series in which police investigations unearth the personal and professional secrets of those involved, both within and outside the law.";
    final String THUMBNAIL = "https://walter.trakt.us/images/shows/000/046/375/thumbs/original/452a9b5dbe.jpg";
    final String LOGO = "https://walter.trakt.us/images/shows/000/046/375/logos/original/ef4294f39a.png";

    List<Show> shows;

    @Before
    public void setup() throws IOException {
        shows = new ShowParser().parse(new FileInputStream("src/test/resources/sample_shows_response.json"));
    }


    @Test
    public void parse_success() {
        assertNotNull(shows);
        assertEquals(3, shows.size());

        Show show = shows.get(0);

        assertEquals("True Detective", show.getTitle());
        assertEquals("46375", show.getId());
        assertEquals("2014", show.getLaunchYear());
        assertEquals(DESCRIPTION, show.getDescription());
        assertEquals(Integer.valueOf(16), show.getTotalEpisodes());

        assertEquals(3, show.getGenres().size());
        assertEquals("mystery", show.getGenres().get(2));

        Images images = show.getImages();

        assertEquals(THUMBNAIL, images.getThumbnail());
        assertEquals(LOGO, images.getLogo());
    }

}