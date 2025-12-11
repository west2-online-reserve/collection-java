import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class CoreModule {
    private Parsedata parsedata=new Parsedata();
    public static List<Athlete> athletes;
    public void displayFinalResults(BufferedWriter writer, String eventName) {
        List<Contest> contests = sortContest(parsedata.parseContest(eventName));
        for (Contest contest : contests){
            if (contest.getFinalrank()==0) continue;
            try {
                writer.write("Full name :" + contest.getAthletes().getLastName() + " " + contest.getAthletes().getFirstName() + "\n"
                +"Rank:"+( contest.getFinalrank()==0?"*":contest.getFinalrank())+ "\n"
                +"Score:"+ parsedata.score(contest.getFinalPoints()) + "\n"
                +"-----"+"\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void displayDetailedResults(BufferedWriter writer, String eventName) {
        List<Contest> contests = sortContest(parsedata.parseContest(eventName));
        for (Contest contest : contests){
            try {
                writer.write("Full name:"+ contest.getAthletes().getLastName() + " " + contest.getAthletes().getFirstName() + "\n"
                +"Rank:"+(contest.getPreliminaryrank()==0?"*":contest.getPreliminaryrank())+ "|"
                        +(contest.getSemifinalrank()==0?"*":contest.getSemifinalrank()) + "|"
                        +(contest.getFinalrank()==0?"*":contest.getFinalrank()) + "\n"
                +"Prelimies score:"+ parsedata.score(contest.getPreliminaryPoints()) + "\n"
                +"Semifinal score:"+ parsedata.score(contest.getSemifinalPoints()) + "\n"
                +"Final score:"+ parsedata.score(contest.getFinalPoints()) + "\n"
                +"-----"+"\n");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        
    }

    public void displayAllPlayers(BufferedWriter writer) {
        athletes = sortAthletes(athletes);
        for (Athlete athlete : athletes){
            try {
                writer.write("Full name :" + athlete.getLastName() + " " + athlete.getFirstName() + "\n"
                +"Gender:"+ athlete.getGender() + "\n"
                +"Country:"+ athlete.getCountry() + "\n"
                +"-----"+"\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        
    }
    public List<Athlete> sortAthletes(List<Athlete> athletes) {
        return athletes.stream()
                .sorted(Comparator
                        .comparing(Athlete::getCountry)
                        .thenComparing(Athlete::getLastName))
                .collect(Collectors.toList());
    }
    public List<Contest> sortContest(List<Contest> contests) {
        return contests.stream()
                .sorted(Comparator
                        .comparing((Contest c) -> c.getAthletes().getCountry())
                        .thenComparing(c -> c.getAthletes().getLastName()))
                .collect(Collectors.toList());
    }

    public void initializeData() {
        this.athletes = parsedata.parseathlete(); }// Return the list of athletes
}
