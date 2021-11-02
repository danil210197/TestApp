package ru.taratorkin.TestApp.model;

public enum TypeTask {
    STRINGS("Найти все подстроки"), MAGIC_SQUARE("Магический квадрат");

    private String translation;

    TypeTask(String translation){
        this.translation = translation;
    }

    @Override
    public String toString() {
        return translation;
    }
}
