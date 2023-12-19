package com.logs.app.repo;

import com.logs.app.model.Track;
import com.logs.app.model.Tracks;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Repository
public class TrackRepository implements TrackInterface {

    private final JPAInterface jpaRepository;
    private float startX = 180;
    private float startY = 90;


    public TrackRepository(JPAInterface jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public ResponseEntity<String> UploadData(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Track track = jpaRepository.findByTrack("bruh");
        if (track != null) {
            if (Objects.equals(fileName, track.getFile()))
                return ResponseEntity.ok().body("File already uploaded");
            jpaRepository.deleteByFileNot(jpaRepository.findByTrack("bruh").getFile());
        }

        if (validateXml(file)) {

            if (fileName == null)
                return ResponseEntity.badRequest().body("Invalid file type");

            Tracks tracks = null;
            if (fileName.endsWith(".kml"))
                tracks = parseKML(file);
            else if (fileName.endsWith(".gpx"))
                tracks = parseGPX(file);


            if (tracks != null) {
                if (jpaRepository.findAll().isEmpty())
                    jpaRepository.save(new Track("bruh", fileName, 0, 0));
                jpaRepository.saveAll(Arrays.asList(tracks.getTracks()));
            }

            return ResponseEntity.ok().body("Ready to go!");
        } else {
            return ResponseEntity.badRequest().body("Invalid xml file");
        }
    }

    @Override
    public String compareTracks(String file) {
        long count = jpaRepository.countDistinctTracksWithSameFileAsBruh(file);
        long count2 = jpaRepository.countDistinctTracksForFileAndTrack();

        double similarity = (double) count / count2 * 100;
        return String.format("%.2f", similarity) + "%";
    }


    private boolean validateXml(MultipartFile file) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            DocumentBuilder builder = factory.newDocumentBuilder();

            builder.parse(file.getInputStream());
            return true;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            return false;
        }
    }


    private Tracks parseKML(MultipartFile file) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file.getInputStream());

            NodeList coordinatesList = document.getElementsByTagName("coordinates");
            Tracks tracks = new Tracks(file.getOriginalFilename());

            for (int i = 0; i < coordinatesList.getLength(); i++) {
                Element coordinates = (Element) coordinatesList.item(i);
                String[] coordinatesArray = coordinates.getTextContent().split("\\s+");

                for (String coordinate : coordinatesArray)
                    if (!coordinate.isEmpty()) {
                        String[] coordinateArray = coordinate.split(",");
                        if (coordinateArray.length >= 2) {
                            double lon = Double.parseDouble(coordinateArray[0]);
                            double lat = Double.parseDouble(coordinateArray[1]);
                            String symbol = ToSymbol((float) lon, (float) lat, 18);
                            tracks.appendTrack(new Track(symbol, file.getOriginalFilename(), (float) lon, (float) lat));
                        }
                    }

            }

            return tracks;

        } catch (ParserConfigurationException | SAXException | IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }


    private Tracks parseGPX(MultipartFile file) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file.getInputStream());
            NodeList trkptList = document.getElementsByTagName("trkpt");
            Tracks tracks = new Tracks(file.getOriginalFilename());
            for (int i = 0; i < trkptList.getLength(); i++) {
                Element trkpt = (Element) trkptList.item(i);
                float lat = Float.parseFloat(trkpt.getAttribute("lat"));
                float lon = Float.parseFloat(trkpt.getAttribute("lon"));
                tracks.appendTrack(new Track(ToSymbol(lon, lat, 18), file.getOriginalFilename(), lon, lat));
            }
            return tracks;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void setStartValues(float startX, float startY) {
        this.startX = startX;
        this.startY = startY;
    }

    private String ToSymbol(float x, float y, int i) {
        return ToSymbolFunc(x, y, i, true);
    }

    private String ToSymbolFunc(float x, float y, int i, boolean bob) {
        if (bob)
            return "0" + ToSymbolFunc(x, y, i - 1, false);

        if (i != 0)
            if (x >= 0 && y >= 0)
                return "1" + ToSymbolFunc(2 * x - startX, 2 * y - startY, i - 1, false);
            else if (x <= 0 && y >= 0)
                return "0" + ToSymbolFunc(2 * x + startX, 2 * y - startY, i - 1, false);
            else if (x <= 0 && y <= 0)
                return "2" + ToSymbolFunc(2 * x + startX, 2 * y + startY, i - 1, false);
            else if (x >= 0 && y <= 0)
                return "3" + ToSymbolFunc(2 * x - startX, 2 * y + startY, i - 1, false);

        return "";
    }
}