/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import view.AdministracaoView;
import view.AtendimentoView;

/**
 *
 * @author Gustavo F. Vetter 28/09/2022
 */
public class Executa {

    public static void main(String[] args) {

        AdministracaoView admView = new AdministracaoView();
        admView.setVisible(true);
        AtendimentoView atView = new AtendimentoView();
        atView.setVisible(true);
    }

}
