package org.example.strategy;

import org.example.dao.entity.Card;

public interface Strategy {

    Card.CardBuilder operate();
}
