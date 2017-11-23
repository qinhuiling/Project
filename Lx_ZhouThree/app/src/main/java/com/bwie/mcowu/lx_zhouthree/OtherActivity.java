package com.bwie.mcowu.lx_zhouthree;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.bwie.mcowu.lx_zhouthree.Base.BaseActivity;
import com.bwie.mcowu.lx_zhouthree.presenter.DownPresenter;
import com.bwie.mcowu.lx_zhouthree.view.IDownView;
import com.bwie.mcowu.lx_zhouthree.widget.media.IjkVideoView;

import okhttp3.ResponseBody;

/*import com.bwie.mcowu.lx_zhouthree.common.PlayerManager;*/

public class OtherActivity extends BaseActivity<DownPresenter> implements /*PlayerManager.PlayerStateListener,*/ View.OnClickListener, IDownView {
    /* private PlayerManager player;*/
    private String video_url;
    private IjkVideoView mViewVideo;
    //String url = "http://video.jiecao.fm/5/1/%E8%87%AA%E5%8F%96%E5%85%B6%E8%BE%B1.mp4";
    // String url = "http://2449.vod.myqcloud.com/2449_bfbbfa3cea8f11e5aac3db03cda99974.f20.mp4";
    String url = "http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4";
    private Button mBtn;
    public static long MAX_SIZE;
    private VideoView mWb;
    private Button mStopBtn;
    private Button mJxBtn;
    private ProgressBar mPb;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        initView();
        Intent intent = getIntent();
        video_url = intent.getStringExtra("video_url");
        Log.i("11111111111111111", "onCreate: " + video_url);
        //  initPlayer();
        presenter.setProgress();


    }

    @Override
    protected void createPresnter() {
        presenter = new DownPresenter(this, this);
    }

    @Override
    protected void init() {

    }

    private void initView() {
       /* mViewVideo = (IjkVideoView) findViewById(R.id.video_view);*/
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
     /*   mWb = (VideoView) findViewById(R.id.wb);*/
        mStopBtn = (Button) findViewById(R.id.btn_stop);
        mStopBtn.setOnClickListener(this);
        mJxBtn = (Button) findViewById(R.id.btn_jx);
        mJxBtn.setOnClickListener(this);

        mPb = (ProgressBar) findViewById(R.id.pb);
        mTv = (TextView) findViewById(R.id.tv);
    }

    /*  private void initPlayer() {
          player = new PlayerManager(this);
          player.setFullScreenOnly(true);
          player.setScaleType(PlayerManager.SCALETYPE_FILLPARENT);
          player.playInFullScreen(true);
          player.setPlayerStateListener(this);
          player.play(url);
      }
  */
   /* @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (player.gestureDetector.onTouchEvent(event))
            return true;
        return super.onTouchEvent(event);
    }

    @Override
    public void onComplete() {
        Log.i("+++++", "onComplete: ");

    }

    @Override
    public void onError() {
        Log.i("+++++", "onError: ");

    }

    @Override
    public void onLoading() {
        Log.i("+++++", "onLoading: ");

    }

    @Override
    public void onPlay() {
        Log.i("+++++", "onPlay: ");
    }
*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                String url2 = getCacheDir() + "haha.mp4";
                presenter.down(String.valueOf(0), url, url2);
                break;
            case R.id.btn_stop:// TODO 17/11/22
                presenter.stop();
                break;
            case R.id.btn_jx:// TODO 17/11/22
                String url3 = getCacheDir() + "haha.mp4";
            presenter.jixu();
                presenter.down(String.valueOf(0), url, url3);
                break;
            default:
                break;
        }
    }

    @Override
    public void getData(ResponseBody responseBody) {

    }

    @Override
    public void getPro(long length) {
        mPb.setMax(100);
        mPb.setProgress((int) length);

        //mTv.setText(length+"%");
    }


}
