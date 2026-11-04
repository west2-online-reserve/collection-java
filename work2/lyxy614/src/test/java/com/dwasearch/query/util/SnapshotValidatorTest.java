package com.dwasearch.query.util;

import com.dwasearch.model.DataSnapshot;
import com.dwasearch.model.players.Country;
import com.dwasearch.model.results.Competition;
import com.dwasearch.query.exception.InvalidDataException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.dwasearch.query.TestData.loadDataSnapshot;
import static org.junit.jupiter.api.Assertions.*;

class SnapshotValidatorTest {
    static final SnapshotValidator snapshotValidator = new SnapshotValidator();

    //测试validate方法
    @Test
    @DisplayName("数据快照为null时validate()应抛出InvalidDataException")
    void validate_null() {
        assertThrows(InvalidDataException.class, ()->snapshotValidator.validate(null));
    }

    @Test
    @DisplayName("读取正常数据validate()不能抛出异常")
    void validate_normal() throws IOException {
        DataSnapshot dataSnapshot = loadDataSnapshot("data.json");
        assertDoesNotThrow(()->snapshotValidator.validate(dataSnapshot));
    }

    //测试validateCountries方法
    @Test
    @DisplayName("国家数量不对需抛出InvalidDataException")
    void validateCountries_wrongCountryNum() throws IOException {
        DataSnapshot dataSnapshot = loadDataSnapshot("data.json");
        List<Country> countries = dataSnapshot.getCountries();
        countries.removeFirst();
        assertThrows(InvalidDataException.class, ()->snapshotValidator.validateCountries(dataSnapshot));
    }

    @Test
    @DisplayName("存在Players数组为空，抛出InvalidDataException")
    void validateCountries_emptyParticipations() throws IOException {
        //在players_empty.json中，手动清空了一个Participations(Players数组)的内容
        DataSnapshot dataSnapshot = loadDataSnapshot("players_empty.json");
        assertThrows(InvalidDataException.class, ()->snapshotValidator.validateCountries(dataSnapshot));
    }

    @Test
    @DisplayName("存在Player的字段缺失，抛出InvalidDataException")
    void validateCountries_missingPlayerFields() throws IOException {
        //在players_missing_fields.json中，手动去除了一个player的PreferredFirstName字段
        DataSnapshot dataSnapshot = loadDataSnapshot("players_missing_fields.json");
        assertThrows(InvalidDataException.class, ()->snapshotValidator.validateCountries(dataSnapshot));
    }

    //测试validateCompetitions方法
    @Test
    @DisplayName("读取正常数据validateCompetitions不能抛出异常")
    void validateCompetitions_normal() throws IOException {
        DataSnapshot dataSnapshot = loadDataSnapshot("data.json");
        assertDoesNotThrow(()->snapshotValidator.validateCompetitions(dataSnapshot));
    }

    @Test
    @DisplayName("比赛数量不对需抛出InvalidDataException")
    void validateCompetitions_wrongCompetitionNum() throws IOException {
        DataSnapshot dataSnapshot = loadDataSnapshot("data.json");
        List<Competition> competitions = dataSnapshot.getCompetitions();
        competitions.removeFirst();
        assertThrows(InvalidDataException.class, ()->snapshotValidator.validate(dataSnapshot));
    }

    @Test
    @DisplayName("存在比赛的disciplineName为null，抛出InvalidDataException")
    void validateCompetitions_nullDisciplineName() throws IOException {
        //competition_null_discipline.json 中删去最后一个比赛的disciplineName
        DataSnapshot dataSnapshot = loadDataSnapshot("competition_null_discipline.json");
        assertThrows(InvalidDataException.class,
                () -> snapshotValidator.validateCompetitions(dataSnapshot));
    }

    @Test
    @DisplayName("存在heat的phaseName为null，抛出InvalidDataException")
    void validateCompetitions_nullPhaseName() throws IOException {
        //competition_null_phase.json 中删去最后一个比赛的heat的phaseName
        DataSnapshot dataSnapshot = loadDataSnapshot("competition_null_phase.json");
        assertThrows(InvalidDataException.class,
                () -> snapshotValidator.validateCompetitions(dataSnapshot));
    }

    @Test
    @DisplayName("存在Competition的决赛阶段数据丢失，抛出InvalidDataException")
    void validateCompetitions_missingFinals() throws IOException {
        //competition_missing_Finals.json中删去了第一个比赛的Heats下的Finals
        DataSnapshot dataSnapshot = loadDataSnapshot("competition_missing_Finals.json");
        assertThrows(InvalidDataException.class,
                () -> snapshotValidator.validateCompetitions(dataSnapshot));
    }

    @Test
    @DisplayName("存在result的rank为null，抛出InvalidDataException")
        //属于Result的字段只测rank，其他同为非List类型的字段省略
    void validateCompetitions_nullRank() throws IOException {
        //competition_null_rank.json 中删去最后一个比赛的Final result的rank
        DataSnapshot dataSnapshot = loadDataSnapshot("competition_null_rank.json");
        assertThrows(InvalidDataException.class,
                () -> snapshotValidator.validateCompetitions(dataSnapshot));
    }

    @Test
    @DisplayName("存在dive的divePoints数组为空，抛出InvalidDataException")
    void validateCompetitions_emptyDivePoints() throws IOException {
        //competition_empty_divepoints.json 中把最后一个比赛的最后一个dive的divePoints数组清空
        DataSnapshot dataSnapshot = loadDataSnapshot("competition_empty_divepoints.json");
        assertThrows(InvalidDataException.class,
                () -> snapshotValidator.validateCompetitions(dataSnapshot));
    }
}