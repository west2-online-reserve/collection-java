package cn.github.lpx.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultShow {
    @JsonProperty("Heats")
    private Heat[] Heats;
    public Heat[] getHeats() {
        return Heats;
    }

    public void setHeats(Heat[] heats) {
        Heats = heats;
    }

    public static class Heat{
        @JsonProperty("PhaseName")
        private String PhaseName;
        @JsonProperty("Results")
        private Result[] Results;
        public String getPhaseName() {
            return PhaseName;
        }
        public void setPhaseName(String PhaseName) {
            this.PhaseName = PhaseName;
        }
        public Result[] getResults() {
            return Results;
        }
        public void setResults(Result[] results) {
            Results = results;
        }

        public static class Result{
            @JsonProperty("Rank")
            private String Rank;
            @JsonProperty("TotalPoints")
            private String TotalPoints;
            @JsonProperty("FullName")
            private String FullName;
            @JsonProperty("Dives")
            private Dive[] Dives;

            public String getRank() {
                return Rank;
            }
            public void setRank(String rank) {
                Rank = rank;
            }
            public String getTotalPoints() {
                return TotalPoints;
            }
            public void setTotalPoints(String totalPoints) {
                TotalPoints = totalPoints;
            }
            public String getFullName() {
                return FullName;
            }
            public void setFullName(String fullName) {
                FullName = fullName;
            }
            public Dive[] getDives() {
                return Dives;
            }
            public void setDives(Dive[] dives) {
                Dives = dives;
            }
            public String showScore(){
                String ans="";
                for(int i=0;i<Dives.length;i++){
                    if(Dives[i].getDivePoints()==null){
                        continue;
                    }
                    ans+=Dives[i].getDivePoints();
                    if(i!=Dives.length-1){
                        ans+=" + ";
                    }
                }
                if(!ans.equals(""))ans+=" = "+TotalPoints;
                else ans="*";
                return ans;
            }

            public static class Dive{
                @JsonProperty("DivePoints")
                private String DivePoints;
                public String getDivePoints() {
                    return DivePoints;
                }
                public void setDivePoints(String divePoints) {
                    DivePoints = divePoints;
                }
            }
        }
    }
}
