package exam.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
class MainController{


    @GetMapping("/home")
    public String homePage(){
        return "home";
    }
}


@RestController
class AnalyticsController{

    // Autowired takes a single instance of the variable and attaches
    // it to every session making it available.
    // Not different from doing "TextRepository repo = new TextRepository()"
    // inside every view.
    @Autowired
    TextRepository repo;


    // Because we use Spring REST to auto-generate a full REST API,
    // we create another endpoint to merge analytics into it.
    // This is not common and shouldn't be done this way, but the alternative
    // better way is more difficult.
    @GetMapping("/analytics")
    public List<HashMap<String, String>> analyzeTexts(){
        // Initialize empty container for storage.
        List<HashMap<String, String>> jsonContainer = new ArrayList<>();

        // Fetch all the documents in the database.
        List<TextEntity> allDocuments = this.repo.findAll();

        // For every document we:
        allDocuments.stream().forEach(document -> {
            // Generate an analytics object with the documents text.
            TextAnalytics analytics = new TextAnalytics(document.getText());

            // Create a storage HashMap to save the end result.
            HashMap<String, String> mergedJSON = new HashMap<>();
            
            // Add all the keys and values of the document to the storage HashMap.
            mergedJSON.putAll(document.toJSON());
            mergedJSON.putAll(analytics.toJSON());  // Add all the keys and values of analytics.
            jsonContainer.add(mergedJSON);  // Add the final result to list.
        });

        return jsonContainer;
    }
}