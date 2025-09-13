public class ContestDetailed {
    public Contest[] contests= new Contest[3];

    public ContestDetailed() {
    }

    public ContestDetailed(Contest[] contests) {
        this.contests = contests;
    }

    /**
     * 获取
     * @return contests
     */
    public Contest[] getContests() {
        return contests;
    }

    /**
     * 设置
     * @param contests
     */
    public void setContests(Contest[] contests) {
        this.contests = contests;
    }

    public String toString() {
        return "ContestDetailed{contests = " + contests.toString() + "}";
    }
}
