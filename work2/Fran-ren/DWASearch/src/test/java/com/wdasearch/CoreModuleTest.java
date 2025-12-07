package com.wdasearch;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CoreModuleTest {

    @Test
    public void displayAllPlayersInfo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            CoreModule.displayAllPlayersInfo(writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void displayResults() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            CoreModule.displayResults(writer,"men 1m springboard");
            CoreModule.displayResults(writer,"men 3m springboard");
            CoreModule.displayResults(writer,"men 3m synchronised");
            CoreModule.displayResults(writer,"men 10m platform");
            CoreModule.displayResults(writer,"men 10m plat");
            CoreModule.displayResults(writer,"men 10m synchronised");
            CoreModule.displayResults(writer,"women 1m springboard");
            CoreModule.displayResults(writer,"women 1m springboard");
            CoreModule.displayResults(writer,"women 3m springboard");
            CoreModule.displayResults(writer,"women 3m synchronised");
            CoreModule.displayResults(writer,"women 10m platform");
            CoreModule.displayResults(writer,"women 10m synchronised");

            CoreModule.displayResults(writer,"men 1m springboard detail");
            CoreModule.displayResults(writer,"men 3m springboard detail");
            CoreModule.displayResults(writer,"men 3m synchronised detail");
            CoreModule.displayResults(writer,"men 10m platform detail");
            CoreModule.displayResults(writer,"men 10m plat detail");
            CoreModule.displayResults(writer,"men 10m synchronised detail");
            CoreModule.displayResults(writer,"women 1m springboard detail");
            CoreModule.displayResults(writer,"women 1m springboard details");
            CoreModule.displayResults(writer,"women 3m springboard detail");
            CoreModule.displayResults(writer,"women 3m synchronised detail");
            CoreModule.displayResults(writer,"women 10m platform detail");
            CoreModule.displayResults(writer,"women 10m synchronised detail");

            CoreModule.displayResults(writer,"women10m synchronised detail");
            CoreModule.displayResults(writer,"acd");
            CoreModule.displayResults(writer,"women 10m synchroniseddetail");
            CoreModule.displayResults(writer,"women 10m synchronised details");
            CoreModule.displayResults(writer,"women 10m synchronised detai");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}