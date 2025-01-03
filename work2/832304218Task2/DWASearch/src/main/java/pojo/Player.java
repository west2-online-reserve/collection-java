/*     */ package pojo;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Objects;
/*     */ import java.util.StringJoiner;
/*     */ 
/*     */ public class Player {
/*     */   private int ResultId;
/*     */   private int Gender;
/*     */   private String PreferredLastName;
/*     */   private String PreferredFirstName;
/*     */   private String NAT;
/*  14 */   private double[] finalScore = new double[10];
/*  15 */   private double[] semifinalScore = new double[10];
/*  16 */   private double[] preliminaryScore = new double[10];
/*     */   private String fullname;
/*     */   private int rank;
/*     */   private double totalScore;
/*     */   private double totalPScore;
/*     */   private double totalSScore;
/*  22 */   private ArrayList<String> rankList = new ArrayList<>();
/*     */   
/*     */   private String ScoreboardPhotoId;
/*     */   private String Sports;
/*     */   private String DOB;
/*     */   private Discipline[] Disciplines;
/*     */   private String PersonId;
/*     */   
/*     */   public double getTotalPScore() {
/*  31 */     return this.totalPScore;
/*     */   }
/*     */   
/*     */   public void setTotalPScore(double totalPScore) {
/*  35 */     this.totalPScore = totalPScore;
/*     */   }
/*     */   
/*     */   public double getTotalSScore() {
/*  39 */     return this.totalSScore;
/*     */   }
/*     */   
/*     */   public void setTotalSScore(double totalSScore) {
/*  43 */     this.totalSScore = totalSScore;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> getRankList() {
/*  49 */     return this.rankList;
/*     */   }
/*     */   
/*     */   public void setRankList(ArrayList<String> rankList) {
/*  53 */     this.rankList = rankList;
/*     */   }
/*     */   
/*     */   public double[] getPreliminaryScore() {
/*  57 */     return this.preliminaryScore;
/*     */   }
/*     */   
/*     */   public void setPreliminaryScore(double[] preliminaryScore) {
/*  61 */     this.preliminaryScore = preliminaryScore;
/*     */   }
/*     */   
/*     */   public double[] getSemifinalScore() {
/*  65 */     return this.semifinalScore;
/*     */   }
/*     */   
/*     */   public void setSemifinalScore(double[] semifinalScore) {
/*  69 */     this.semifinalScore = semifinalScore;
/*     */   }
/*     */   
/*     */   public double getTotalScore() {
/*  73 */     return this.totalScore;
/*     */   }
/*     */   
/*     */   public void setTotalScore(double totalScore) {
/*  77 */     this.totalScore = totalScore;
/*     */   }
/*     */   
/*     */   public int getRank() {
/*  81 */     return this.rank;
/*     */   }
/*     */   
/*     */   public void setRank(int rank) {
/*  85 */     this.rank = rank;
/*     */   }
/*     */   
/*     */   public String getFullname() {
/*  89 */     return this.fullname;
/*     */   }
/*     */   
/*     */   public void setFullname(String fullname) {
/*  93 */     this.fullname = fullname;
/*     */   }
/*     */ 
/*     */   
/*     */   public Player() {}
/*     */   
/*     */   public Player(int resultId, int gender, String preferredLastName, String preferredFirstName, String scoreboardPhotoId, String sports, String DOB, String NAT, Discipline[] disciplines, String personId, double[] score) {
/* 100 */     this.ResultId = resultId;
/* 101 */     this.Gender = gender;
/* 102 */     this.PreferredLastName = preferredLastName;
/* 103 */     this.PreferredFirstName = preferredFirstName;
/* 104 */     this.ScoreboardPhotoId = scoreboardPhotoId;
/* 105 */     this.Sports = sports;
/* 106 */     this.DOB = DOB;
/* 107 */     this.NAT = NAT;
/* 108 */     this.Disciplines = disciplines;
/* 109 */     this.PersonId = personId;
/* 110 */     this.finalScore = score;
/*     */   }
/*     */   
/*     */   public String getPersonId() {
/* 114 */     return this.PersonId;
/*     */   }
/*     */   
/*     */   public void setPersonId(String personId) {
/* 118 */     this.PersonId = personId;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getResultId() {
/* 123 */     return this.ResultId;
/*     */   }
/*     */   
/*     */   public void setResultId(int resultId) {
/* 127 */     this.ResultId = resultId;
/*     */   }
/*     */   
/*     */   public int getGender() {
/* 131 */     return this.Gender;
/*     */   }
/*     */   
/*     */   public void setGender(int gender) {
/* 135 */     this.Gender = gender;
/*     */   }
/*     */   
/*     */   public String getPreferredLastName() {
/* 139 */     return this.PreferredLastName;
/*     */   }
/*     */   
/*     */   public void setPreferredLastName(String preferredLastName) {
/* 143 */     this.PreferredLastName = preferredLastName;
/*     */   }
/*     */   
/*     */   public String getPreferredFirstName() {
/* 147 */     return this.PreferredFirstName;
/*     */   }
/*     */   
/*     */   public void setPreferredFirstName(String preferredFirstName) {
/* 151 */     this.PreferredFirstName = preferredFirstName;
/*     */   }
/*     */   
/*     */   public String getScoreboardPhotoId() {
/* 155 */     return this.ScoreboardPhotoId;
/*     */   }
/*     */   
/*     */   public void setScoreboardPhotoId(String scoreboardPhotoId) {
/* 159 */     this.ScoreboardPhotoId = scoreboardPhotoId;
/*     */   }
/*     */   
/*     */   public String getSports() {
/* 163 */     return this.Sports;
/*     */   }
/*     */   
/*     */   public void setSports(String sports) {
/* 167 */     this.Sports = sports;
/*     */   }
/*     */   
/*     */   public String getDOB() {
/* 171 */     return this.DOB;
/*     */   }
/*     */   
/*     */   public void setDOB(String DOB) {
/* 175 */     this.DOB = DOB;
/*     */   }
/*     */   
/*     */   public Discipline[] getDisciplines() {
/* 179 */     return this.Disciplines;
/*     */   }
/*     */   
/*     */   public void setDisciplines(Discipline[] disciplines) {
/* 183 */     this.Disciplines = disciplines;
/*     */   }
/*     */   
/*     */   public String getNAT() {
/* 187 */     return this.NAT;
/*     */   }
/*     */   
/*     */   public void setNAT(String NAT) {
/* 191 */     this.NAT = NAT;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 196 */     if (this.Gender == 1) {
/* 197 */       return "Full Name:" + this.PreferredLastName + " " + this.PreferredFirstName + "\r\nGender:Female\r\nNationality:" + this.NAT;
/*     */     }
/*     */ 
/*     */     
/* 201 */     return "Full Name:" + this.PreferredLastName + " " + this.PreferredFirstName + "\r\nGender:Male\r\nNationality:" + this.NAT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getFinalScore() {
/* 209 */     return this.finalScore;
/*     */   }
/*     */   
/*     */   public void setFinalScore(double[] finalScore) {
/* 213 */     this.finalScore = finalScore;
/*     */   }
/*     */ 
/*     */   
/*     */   public String finalShow() {
/* 218 */     StringJoiner joiner = new StringJoiner("+");
/* 219 */     for (double v : this.finalScore) {
/* 220 */       if (v != 0.0D) {
/* 221 */         joiner.add(v + "");
/*     */       }
/*     */     } 
/*     */     
/* 225 */     return "Full Name:" + this.fullname.substring(1, this.fullname.length() - 1) + "\r\nRank:" + this.rank + "\r\nScore:" + joiner
/*     */       
/* 227 */       .toString() + " = " + this.totalScore;
/*     */   }
/*     */ 
/*     */   
/*     */   public String detailShow() {
/* 232 */     StringBuilder stringBuilder = new StringBuilder();
/* 233 */     if (this.rankList.size() < 3) {
/* 234 */       while (this.rankList.size() < 3) {
/* 235 */         this.rankList.add("*");
/*     */       }
/*     */     }
/* 238 */     StringJoiner joiner = new StringJoiner(" | ");
/* 239 */     for (String s : this.rankList) {
/* 240 */       joiner.add(s);
/*     */     }
/*     */     
/* 243 */     StringJoiner joiner1 = new StringJoiner("+");
/* 244 */     boolean flag1 = false;
/* 245 */     for (double value : this.preliminaryScore) {
/* 246 */       if (value != 0.0D) {
/* 247 */         flag1 = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 251 */     if (flag1) {
/* 252 */       for (double v : this.preliminaryScore) {
/* 253 */         if (v != 0.0D) {
/* 254 */           joiner1.add(v + "");
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 260 */     StringJoiner joiner2 = new StringJoiner("+");
/* 261 */     boolean flag2 = false;
/* 262 */     for (double value : this.semifinalScore) {
/* 263 */       if (value != 0.0D) {
/* 264 */         flag2 = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 268 */     if (flag2) {
/* 269 */       for (double v : this.semifinalScore) {
/* 270 */         if (v != 0.0D) {
/* 271 */           joiner2.add(v + "");
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 278 */     System.out.println("Full Name:" + this.fullname.substring(1, this.fullname.length() - 1) + "\r\nRank:" + joiner
/* 279 */         .toString());
/* 280 */     stringBuilder.append("Full Name:").append(this.fullname.substring(1, this.fullname.length() - 1));
/* 281 */     stringBuilder.append("\r\n");
/* 282 */     stringBuilder.append("Rank:").append(joiner.toString());
/* 283 */     stringBuilder.append("\r\n");
/* 284 */     if (flag1) {
/* 285 */       System.out.println("Preliminary Score:" + joiner1.toString() + " = " + this.totalPScore);
/* 286 */       stringBuilder.append("Preliminary Score:").append(joiner1.toString()).append(" = ").append(this.totalPScore);
/* 287 */       stringBuilder.append("\r\n");
/*     */     } 
/*     */     
/* 290 */     if (flag2) {
/* 291 */       System.out.println("Semifinal Score:" + joiner2.toString() + " = " + this.totalSScore);
/* 292 */       stringBuilder.append("Semifinal Score:").append(joiner2.toString()).append(" = ").append(this.totalSScore);
/* 293 */       stringBuilder.append("\r\n");
/*     */     } 
/*     */     
/* 296 */     StringJoiner joiner3 = new StringJoiner("+");
/* 297 */     for (double v : this.finalScore) {
/* 298 */       if (v != 0.0D) {
/* 299 */         joiner3.add(v + "");
/*     */       }
/*     */     } 
/* 302 */     if (!((String)this.rankList.get(2)).equals("*")) {
/* 303 */       System.out.println("Final Score:" + joiner3.toString() + " = " + this.totalScore);
/* 304 */       stringBuilder.append("Final Score:").append(joiner3.toString()).append(" = ").append(this.totalScore);
/* 305 */       stringBuilder.append("\r\n");
/*     */     } 
/*     */     
/* 308 */     System.out.println("-----");
/* 309 */     stringBuilder.append("-----");
/* 310 */     stringBuilder.append("\r\n");
/*     */     
/* 312 */     return stringBuilder.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 318 */     if (o == null || getClass() != o.getClass()) {
/* 319 */       return false;
/*     */     }
/* 321 */     Player player = (Player)o;
/* 322 */     return (this.ResultId == player.ResultId && this.Gender == player.Gender && this.rank == player.rank && Double.compare(this.totalScore, player.totalScore) == 0 && Double.compare(this.totalPScore, player.totalPScore) == 0 && Double.compare(this.totalSScore, player.totalSScore) == 0 && Objects.equals(this.PreferredLastName, player.PreferredLastName) && Objects.equals(this.PreferredFirstName, player.PreferredFirstName) && Objects.equals(this.ScoreboardPhotoId, player.ScoreboardPhotoId) && Objects.equals(this.Sports, player.Sports) && Objects.equals(this.DOB, player.DOB) && Objects.equals(this.NAT, player.NAT) && Objects.deepEquals(this.Disciplines, player.Disciplines) && Objects.equals(this.PersonId, player.PersonId) && Objects.deepEquals(this.finalScore, player.finalScore) && Objects.deepEquals(this.semifinalScore, player.semifinalScore) && Objects.deepEquals(this.preliminaryScore, player.preliminaryScore) && Objects.equals(this.fullname, player.fullname) && Objects.equals(this.rankList, player.rankList));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 327 */     return Objects.hash(new Object[] { Integer.valueOf(this.ResultId), Integer.valueOf(this.Gender), this.PreferredLastName, this.PreferredFirstName, this.ScoreboardPhotoId, this.Sports, this.DOB, this.NAT, Integer.valueOf(Arrays.hashCode((Object[])this.Disciplines)), this.PersonId, Integer.valueOf(Arrays.hashCode(this.finalScore)), Integer.valueOf(Arrays.hashCode(this.semifinalScore)), Integer.valueOf(Arrays.hashCode(this.preliminaryScore)), this.fullname, Integer.valueOf(this.rank), Double.valueOf(this.totalScore), Double.valueOf(this.totalPScore), Double.valueOf(this.totalSScore), this.rankList });
/*     */   }
/*     */ }


/* Location:              C:\Users\27705\Desktop\WestOnlineTask2-1.0-SNAPSHOT-jar-with-dependencies.jar!\com\westonline\pojo\Player.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */