package customizedalphabet_test;

import model.CustomizedAlphabet;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TestingClearAlphabet {
    private class TestingCustomizedAlphabet extends CustomizedAlphabet{

        /**
         * Конструктор класса
         *
         * @param alphabet алфавит, который будет использоваться в игре
         * @throws IllegalArgumentException если алфавит состоит из букв разного алфавита или пуст
         */
        public TestingCustomizedAlphabet(@NotNull String alphabet) {
            super(alphabet);
        }

        @Override
        public void blockLetter() {
            super.blockLetter();
        }
    }

    TestingCustomizedAlphabet alphabet = new TestingCustomizedAlphabet("фыв");

    @Test
    public void simpleClear(){
        alphabet.blockLetter();
        alphabet.blockLetter();
        alphabet.clearBlockedChars();

        Assertions.assertTrue(alphabet.getBlockedChars().isEmpty());
    }
}
