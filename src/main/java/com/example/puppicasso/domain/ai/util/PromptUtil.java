package com.example.puppicasso.domain.ai.util;

import com.example.puppicasso.domain.ai.prompt.Atmosphere;
import com.example.puppicasso.domain.ai.prompt.StudioConcept;

public class PromptUtil {
    public static String generatePrompt(Atmosphere atmosphere, StudioConcept studio) {
        String promptTemplate = "Edit the provided image to generate a %s, high-resolution portrait of the dog. "
                + "Remove the existing background and replace it with a setting that matches %s. "
                + "Ensure the dog in the edited image matches the provided image exactly, including all features (fur color, eye color, body shape, nose, eyes, mouth, ears). "
                + "The new background should be %s. "
                + "The image should appear as if it was taken in a professional studio, looking natural, lifelike, and highly detailed.";

        String description = atmosphere.getPrompt();
        String studioDescription = studio.getPrompt();

        return String.format(promptTemplate, atmosphere.name().toLowerCase(), studioDescription, description);
    }
}

