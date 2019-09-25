package residentevil.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import residentevil.services.VirusService;

@Controller
public class MapController extends BaseController {

    @Autowired
    private VirusService virusService;


    @GetMapping("/map")
    public String getMapPage(Model model) {
        String geoJson = this.virusService.getAllVirusesWithAffectedCapitals();
        model.addAttribute("geoJson", geoJson);
        return "map";
    }
}
