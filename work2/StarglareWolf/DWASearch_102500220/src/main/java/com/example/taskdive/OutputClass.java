package com.example.taskdive;

public class OutputClass {

    // 运动员输出
    public static void OutputAthlete(Athlete athlete)
    {
        String sex = (athlete.getGender() == 0) ? "Male" : "Female";
        athlete.setCountryName((athlete.getCountryName()  == null) ?
                athlete.getCountryNameFromCode(athlete.getCountryCode()) : athlete.getCountryName());
        System.out.println("Full Name: " + athlete.getPreferredLastName() + " " + athlete.getPreferredFirstName());
        System.out.println("Gender: " + sex);
        System.out.println("Country: " + athlete.getCountryName());
        System.out.println("----");
    }

    //排名、成绩输出
    public static void OutputRankPoint(RankPoint rankPoint)
    {
        System.out.println("Rank: " + rankPoint.getDiveRank());
        for(Athlete athlete: rankPoint.getAthletes())
        {
            OutputAthlete(athlete);
        }
        System.out.print("DivePoints: ");
        int total = rankPoint.getDivePoints().size();
        if(rankPoint.getCarryOverPoints() != null)
        {
            System.out.print(rankPoint.getCarryOverPoints() + " + ");
        }
        for(int i = 0;i < total; i++)
        {
            System.out.print(rankPoint.getDivePoints().get(i));
            if(i != total - 1)
                System.out.print(" + ");
            else
                System.out.print(" = ");
        }
        System.out.println(rankPoint.getTotalPoints());
        System.out.println("###########");
    }

    // 赛事阶段输出
    public static void OutputPhase(Phase phase)
    {
        if (phase.getPhaseName().equals("Final") || phase.getPhaseName().equals("Finals")) {
            System.out.println("Phase: " + phase.getPhaseName());
            System.out.println("----");
            for (RankPoint point : phase.getRankPoint()) {
                OutputRankPoint(point);
            }
        }
    }

    // 赛事输出
    public static void OutputDisciplines(Disciplines disciplines)
    {
        System.out.println("------------DisciplineName: " + disciplines.getDisciplineName() + "------------");
        for(Phase phase: disciplines.getPhases())
        {
            OutputPhase(phase);
        }
    }
}
