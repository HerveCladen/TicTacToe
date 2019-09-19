package com.example.draweractivity.ui.play;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.draweractivity.R;

public class PlayFragment extends Fragment {

    private PlayViewModel playViewModel;
    public int turn = 0;
    public int check;
    public Button[][] btnMatrix;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        playViewModel =
                ViewModelProviders.of(this).get(PlayViewModel.class);
        View root = inflater.inflate(R.layout.fragment_play, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        playViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button btn1 = root.findViewById(R.id.btn_1);
        Button btn2 = root.findViewById(R.id.btn_2);
        Button btn3 = root.findViewById(R.id.btn_3);
        Button btn4 = root.findViewById(R.id.btn_4);
        Button btn5 = root.findViewById(R.id.btn_5);
        Button btn6 = root.findViewById(R.id.btn_6);
        Button btn7 = root.findViewById(R.id.btn_7);
        Button btn8 = root.findViewById(R.id.btn_8);
        Button btn9 = root.findViewById(R.id.btn_9);
        btnMatrix = new Button[][] { { btn1, btn2, btn3 },
                                     { btn4, btn5, btn6 },
                                     { btn7, btn8, btn9 } };
        for (Button[] btnArray : btnMatrix) {
            for (Button btn : btnArray) {
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickBtn((Button) view);
                    }
                });
            }
        }

        return root;
    }

    public void onClickBtn(Button b) {
        if(b.getText() == "") {
            if(turn % 2 == 0)
                b.setText("X");
            else
                b.setText("O");
            turn++;
        }
        checkWinner();
    }

    public void checkWinner(){
        boolean winner = false;
        check = 0;

        // check columns
        for (int i = 0; i < 3 && !winner; i++) {
            check = 0;
            for (int j = 0; j < 3; j++) {
                if (btnMatrix[i][j].getText() == "X") {
                    check++;
                } else if (btnMatrix[i][j].getText() == "O") {
                    check--;
                } else break;
            }
            if(Math.abs(check) == 3){
                winner = true;
            }
        }

        // check rows
        for (int i = 0; i < 3 && !winner; i++) {
            check = 0;
            for (int j = 0; j < 3; j++) {
                if (btnMatrix[j][i].getText() == "X") {
                    check++;
                } else if (btnMatrix[j][i].getText() == "O") {
                    check--;
                } else break;
            }
            if(Math.abs(check) == 3){
                winner = true;
            }
        }

        // check straight diagonal
        if (!winner) {
            check = 0;
            for (int i = 0; i < 3; i++) {
                if (btnMatrix[i][i].getText() == "X") {
                    check++;
                } else if (btnMatrix[i][i].getText() == "O") {
                    check--;
                } else break;
            }
            if (Math.abs(check) == 3) {
                winner = true;
            }
        }

        // check reverse diagonal
        if (!winner) {
            check = 0;
            for (int i = 0; i < 3; i++) {
                if (btnMatrix[i][2 - i].getText() == "X") {
                    check++;
                } else if (btnMatrix[i][2 - i].getText() == "O") {
                    check--;
                } else break;
            }
            if (Math.abs(check) == 3) {
                winner = true;
            }
        }

        // check end conditions
        if(turn == 9 && !winner) {
            new AlertDialog.Builder(this.getContext())
                    .setTitle("Game over")
                    .setMessage("No space remaining.")
                    .show();
        } else if (winner) {
            for (Button[] btnArray : btnMatrix) {
                for (Button btn : btnArray) {
                    btn.setEnabled(false);
                }
            }
            if(check == 3) {
                new AlertDialog.Builder(this.getContext())
                        .setTitle("Winner!")
                        .setMessage("X won!")
                        .show();
            } else {
                new AlertDialog.Builder(this.getContext())
                        .setTitle("Winner!")
                        .setMessage("O won!")
                        .show();
            }
        }
    }
}