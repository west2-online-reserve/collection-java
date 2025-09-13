public interface ParseInterface {
    //解析运动员信息json文件
    public void parserPlayerInfo(String fileName);
    //parser所有项目的决赛结果（简略）
    public void parserResult(String fileName);
    //parser所有项目的结果(详细）
    public void parserResultDetail(String fileName);

}
