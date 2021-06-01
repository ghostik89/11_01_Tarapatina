package model;

import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;

public class HarderGame extends Game{
    BlockedAlphabet blockedAlphabet = new BlockedAlphabet("абвгдежзийклмнопрстуфхцчшщъыьэюя");
    /**
     * Конструктор класса
     *
     * @param field       игровое поле
     * @param player1Name имя первого игрока
     * @param player2Name имя второго игрока
     * @throws IllegalArgumentException если имена игроков совпадают
     */
    public HarderGame(@NotNull GameField field, @NotNull String player1Name, @NotNull String player2Name) throws FileNotFoundException {
        super(field, player1Name, player2Name);
    }

    /**
     * Получить текущий алфавит
     */
    @Override
    public BlockedAlphabet getAlphabet() {
        return this.blockedAlphabet;
    }
}
