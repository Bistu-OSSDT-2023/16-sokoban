package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.util.Random;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private ImageView playerImageView;
    private ImageView boxImageView;
    private ImageView wallImageView;
    private ImageView wallImageView1;
    private ImageView wallImageView2;
    private ImageView blackHoleImageView;
    private Button upButton;
    private Button downButton;
    private Button leftButton;
    private Button rightButton;

    private int playerPosX = 1;
    private int playerPosY = 1;

    private int boxPosX = 2;
    private int boxPosY = 8;
    private int wallPosX = 1;
    private int wallPosY = 2;

    private int wallPosX1 = 2;

    private int wallPosY1 = 4;

    private int wallPosX2 = 5;

    private int wallPosY2 = 3;

    int blackHolePosX = 6;
    int blackHolePosY = 6;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        wallImageView = findViewById(R.id.wallImageView);
        wallImageView.setImageResource(R.drawable.qiangti);

        wallImageView1 = findViewById(R.id.wallImageView1);
        wallImageView1.setImageResource(R.drawable.qiangti);

        wallImageView2 = findViewById(R.id.wallImageView2);
        wallImageView2.setImageResource(R.drawable.qiangti);

        blackHoleImageView = findViewById(R.id.blackHoleImageView);
        blackHoleImageView.setImageResource(R.drawable.chengbao);

        wallImageView = findViewById(R.id.wallImageView);
        wallImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 切换图片
                wallImageView.setImageResource(R.drawable.qiangti);
            }
        });

        wallImageView1 = findViewById(R.id.wallImageView1);
        wallImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 切换图片
                wallImageView1.setImageResource(R.drawable.qiangti);
            }
        });

        wallImageView2 = findViewById(R.id.wallImageView2);
        wallImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 切换图片
                wallImageView2.setImageResource(R.drawable.qiangti);
            }
        });

        playerImageView = findViewById(R.id.playerImageView);
        playerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 切换人物图片
                playerImageView.setImageResource(R.drawable.kong);
            }
        });

        boxImageView = findViewById(R.id.boxImageView);
        boxImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 切换箱子图片
                boxImageView.setImageResource(R.drawable.box);
            }
        });

        blackHoleImageView = findViewById(R.id.blackHoleImageView);
        blackHoleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 切换图片
                blackHoleImageView.setImageResource(R.drawable.chengbao);
            }
        });

        upButton = findViewById(R.id.upButton);
        downButton = findViewById(R.id.downButton);
        leftButton = findViewById(R.id.leftButton);
        rightButton = findViewById(R.id.rightButton);


        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movePlayer(0, -1);
            }
        });

        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movePlayer(0, 1);
            }
        });

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movePlayer(-1, 0);
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movePlayer(1, 0);
            }
        });
    }



    private void movePlayer(int deltaX, int deltaY) {
        int nextPlayerPosX = playerPosX + deltaX;
        int nextPlayerPosY = playerPosY + deltaY;

        if (canMove(nextPlayerPosX, nextPlayerPosY)) {
            if (nextPlayerPosX == boxPosX && nextPlayerPosY == boxPosY) {
                // 玩家在箱子的上下左右位置时才能拖动箱子
                if (deltaX != boxPosX && deltaY == boxPosY) {
                    int nextBoxPosX = boxPosX + deltaX;
                    int nextBoxPosY = boxPosY + deltaY;

                    if (canMove(nextBoxPosX, nextBoxPosY)) {
                        boxPosX = nextBoxPosX;
                        boxPosY = nextBoxPosY;
                    } else {
                        return; // 如果箱子不能移动，不更新玩家位置
                    }
                } else if (deltaX ==boxPosX  && deltaY != boxPosY) {
                    int nextBoxPosX = boxPosX + deltaX;
                    int nextBoxPosY = boxPosY + deltaY;

                    if (canMove(nextBoxPosX, nextBoxPosY)) {
                        boxPosX = nextBoxPosX;
                        boxPosY = nextBoxPosY;
                    } else {
                        return; // 如果箱子不能移动，不更新玩家位置
                    }
                } else {
                    return; // 如果玩家不在箱子的上下左右位置，不更新箱子位置
                }
            } else{
                boxPosX = playerPosX; // 更新箱子位置为玩家之前的位置
                boxPosY = playerPosY;
            }

            playerPosX = nextPlayerPosX;
            playerPosY = nextPlayerPosY;

            // 判断箱子是否移动到黑洞位置
            int nextBoxPosX = boxPosX + deltaX;
            int nextBoxPosY = boxPosY + deltaY;
            if (nextBoxPosX == blackHolePosX && nextBoxPosY == blackHolePosY) {
                gameWin();
            }

            updateGameView();
        }
    }

    private boolean canPushBox = false;

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();

            int playerWidth = playerImageView.getWidth();
            int playerHeight = playerImageView.getHeight();

            int boxWidth = boxImageView.getWidth();
            int boxHeight = boxImageView.getHeight();

            int playerLeft = (int) playerImageView.getX();
            int playerTop = (int) playerImageView.getY();
            int playerRight = playerLeft + playerWidth;
            int playerBottom = playerTop + playerHeight;

            if (canPushBox) {
                // 玩家在箱子的左边
                if (playerPosX == boxPosX && playerPosY == boxPosY - 1 && x >= playerLeft - boxWidth && x <= playerRight &&
                        y >= playerTop && y <= playerBottom) {
                    movePlayer(0, -1);
                }
                // 玩家在箱子的右边
                else if (playerPosX == boxPosX && playerPosY == boxPosY + 1 && x >= playerLeft && x <= playerRight + boxWidth &&
                        y >= playerTop && y <= playerBottom) {
                    movePlayer(0, 1);
                }
                // 玩家在箱子的上边
                else if (playerPosY == boxPosY && playerPosX == boxPosX - 1 && x >= playerLeft && x <= playerRight &&
                        y >= playerTop - boxHeight && y <= playerBottom) {
                    movePlayer(-1, 0);
                }
                // 玩家在箱子的下边
                else if (playerPosY == boxPosY && playerPosX == boxPosX + 1 && x >= playerLeft && x <= playerRight &&
                        y >= playerTop && y <= playerBottom + boxHeight) {
                    movePlayer(1, 0);
                }
            }
        }
        return super.onTouchEvent(event);
    }
    private boolean canMove(int x, int y) {
        if (x <  0|| x >= 8 || y <  0|| y >= 8) {
            return false;
        }
        if ((x == wallPosX && y == wallPosY) ||
                    (x == wallPosX1 && y == wallPosY1) ||
                    (x == wallPosX2 && y == wallPosY2) ||
                    (x == boxPosX && y == boxPosY)) {
                return false; // 如果下一步要移动到墙或箱子的位置，返回false表示不能移动
            }
        if (x == boxPosX && y == boxPosY) {
            int nextBoxPosX = boxPosX + (x - playerPosX);
            int nextBoxPosY = boxPosY + (y - playerPosY);
            if (nextBoxPosX < 0 || nextBoxPosX >= 8 || nextBoxPosY < 0 || nextBoxPosY >= 8 ||
                    (nextBoxPosX == wallPosX && nextBoxPosY == wallPosY && nextBoxPosX == wallPosX1 && nextBoxPosY == wallPosY1&& nextBoxPosX == wallPosX2 && nextBoxPosY == wallPosY2)) {
                return false;
            }
            if (x == playerPosX) {
                canPushBox = true;
            }
            if (y == playerPosY) {
                canPushBox = true;
            }
        }
        return true;
    }


    private void updateGameView() {
        playerImageView.setX(playerPosX * playerImageView.getWidth());
        playerImageView.setY(playerPosY * playerImageView.getHeight());

        boxImageView.setX(boxPosX * boxImageView.getWidth());
        boxImageView.setY(boxPosY * boxImageView.getHeight());

        blackHoleImageView.setX(blackHolePosX *  blackHoleImageView.getWidth());
        blackHoleImageView.setY(blackHolePosY *  blackHoleImageView.getHeight());

        RelativeLayout.LayoutParams wallParams = (RelativeLayout.LayoutParams) wallImageView.getLayoutParams();
        wallParams.leftMargin = wallPosX * wallImageView.getWidth();
        wallParams.topMargin = wallPosY * wallImageView.getHeight();
        wallImageView.setLayoutParams(wallParams);

        RelativeLayout.LayoutParams wallParams1 = (RelativeLayout.LayoutParams) wallImageView1.getLayoutParams();
        wallParams1.leftMargin = wallPosX1 * wallImageView1.getWidth();
        wallParams1.topMargin = wallPosY1 * wallImageView1.getHeight();
        wallImageView1.setLayoutParams(wallParams1);

        RelativeLayout.LayoutParams wallParams2 = (RelativeLayout.LayoutParams) wallImageView2.getLayoutParams();
        wallParams2.leftMargin = wallPosX2 * wallImageView2.getWidth();
        wallParams2.topMargin = wallPosY2 * wallImageView2.getHeight();
        wallImageView2.setLayoutParams(wallParams2);

    }

    private boolean isGameOver = false;


    private void gameWin() {
        // 游戏胜利逻辑代码
        isGameOver = true;

        // 显示游戏结束的界面
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("游戏胜利");
        builder.setMessage("恭喜你完成了游戏！\n注意：游戏内容会发生改变");
        builder.setPositiveButton("再来一次", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 重新开始游戏
                resetGame();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    private void resetGame() {

        Random random = new Random();

        // 重置玩家、箱子和黑洞的位置

        playerPosX = random.nextInt(7) + 1;
        playerPosY = random.nextInt(7) + 1;

        boxPosX = random.nextInt(7) + 1;
        boxPosY = random.nextInt(7) + 1;

        wallPosX = random.nextInt(8) ;
        wallPosX = random.nextInt(8) ;

        wallPosX1 = random.nextInt(8) ;
        wallPosX1 = random.nextInt(8) ;

        wallPosX2 = random.nextInt(8) ;
        wallPosX2 = random.nextInt(8) ;

        blackHolePosX = random.nextInt(8);
        blackHolePosY = random.nextInt(8);


        do {
            playerPosX = random.nextInt(7) + 1;
            playerPosY = random.nextInt(7) + 1;
        } while (playerPosX == boxPosX && playerPosY == boxPosY);

        // 如果黑洞位置需要重置，请在这里添加对应的代码

        // 重置游戏结束状态
        isGameOver = false;

        // 更新游戏界面
        updateGameView();
    }
}