/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.dto;

import java.util.List;

/**
 *
 * @author johnson3yo
 */
public class Voices {

    
    
    public Voices() {
    }

    public List<Voice> getVoices() {
        return voices;
    }

    public void setVoices(List<Voice> voices) {
        this.voices = voices;
    }
 
    List<Voice> voices;

    static class Voice {

        public Voice() {
        }

        String[] languageCodes;
        String name;
        SsmlVoiceGender ssmlGender;
        Integer naturalSampleRateHertz;

        enum SsmlVoiceGender {
            SSML_VOICE_GENDER_UNSPECIFIED, MALE, FEMALE, NEUTRAL
        }

        public String[] getLanguageCodes() {
            return languageCodes;
        }

        public void setLanguageCodes(String[] languageCodes) {
            this.languageCodes = languageCodes;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public SsmlVoiceGender getSsmlGender() {
            return ssmlGender;
        }

        public void setSsmlGender(SsmlVoiceGender ssmlGender) {
            this.ssmlGender = ssmlGender;
        }

        public Integer getNaturalSampleRateHertz() {
            return naturalSampleRateHertz;
        }

        public void setNaturalSampleRateHertz(Integer naturalSampleRateHertz) {
            this.naturalSampleRateHertz = naturalSampleRateHertz;
        }
        
        

        @Override
        public String toString() {
            return "Voice{" + "languageCodes=" + languageCodes + ", name=" + name + ", ssmlGender=" + ssmlGender + ", naturalSampleRateHertz=" + naturalSampleRateHertz + '}';
        }

    }

    @Override
    public String toString() {
        return "Voices{" + "voices=" + voices + '}';
    }

}
