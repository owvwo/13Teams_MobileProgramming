// Generated by view binder compiler. Do not edit!
package com.example.mvplayer.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.mvplayer.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class RowBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView NoText;

  @NonNull
  public final TextView newstitle;

  @NonNull
  public final ImageView playlistimg;

  @NonNull
  public final TextView singer;

  @NonNull
  public final ImageView thumnailimg;

  @NonNull
  public final TextView videoId;

  private RowBinding(@NonNull LinearLayout rootView, @NonNull TextView NoText,
      @NonNull TextView newstitle, @NonNull ImageView playlistimg, @NonNull TextView singer,
      @NonNull ImageView thumnailimg, @NonNull TextView videoId) {
    this.rootView = rootView;
    this.NoText = NoText;
    this.newstitle = newstitle;
    this.playlistimg = playlistimg;
    this.singer = singer;
    this.thumnailimg = thumnailimg;
    this.videoId = videoId;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static RowBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static RowBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.row, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static RowBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.NoText;
      TextView NoText = rootView.findViewById(id);
      if (NoText == null) {
        break missingId;
      }

      id = R.id.newstitle;
      TextView newstitle = rootView.findViewById(id);
      if (newstitle == null) {
        break missingId;
      }

      id = R.id.playlistimg;
      ImageView playlistimg = rootView.findViewById(id);
      if (playlistimg == null) {
        break missingId;
      }

      id = R.id.singer;
      TextView singer = rootView.findViewById(id);
      if (singer == null) {
        break missingId;
      }

      id = R.id.thumnailimg;
      ImageView thumnailimg = rootView.findViewById(id);
      if (thumnailimg == null) {
        break missingId;
      }

      id = R.id.videoId;
      TextView videoId = rootView.findViewById(id);
      if (videoId == null) {
        break missingId;
      }

      return new RowBinding((LinearLayout) rootView, NoText, newstitle, playlistimg, singer,
          thumnailimg, videoId);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}