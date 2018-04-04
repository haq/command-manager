package me.ihaq.commandmanager;

public class Main {

    public static void main(String[] args) {

    }

    private static class TestCommand implements Command {

        @Override
        public boolean onCommand(String label, String[] args) {
            return false;
        }

        @Override
        public String usage() {
            return null;
        }
    }
}
