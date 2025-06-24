package com.example.swiki;

public class CharacterResponse {
    private Result result;

    public Result getResult() {
        return result;
    }

    public static class Result {
        private Properties properties;

        public Properties getProperties() {
            return properties;
        }
    }

    public static class Properties {
        private String name, gender, skin_color, hair_color, height, eye_color, birth_year;

        public String getName() { return name; }
        public String getGender() { return gender; }
        public String getSkinColor() { return skin_color; }
        public String getHairColor() { return hair_color; }
        public String getHeight() { return height; }
        public String getEyeColor() { return eye_color; }
        public String getBirthYear() { return birth_year; }
    }
}
