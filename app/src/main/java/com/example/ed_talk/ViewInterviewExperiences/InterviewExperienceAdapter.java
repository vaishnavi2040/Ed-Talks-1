package com.example.ed_talk.ViewInterviewExperiences;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ed_talk.Modals.InterviewExperience;
import com.example.ed_talk.Modals.Post;
import com.example.ed_talk.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class InterviewExperienceAdapter extends RecyclerView.Adapter<InterviewExperienceAdapter.InterviewExperienceViewHolder> {

    private Context myContext;

    //creating list to store all departments
    private List<InterviewExperience> placementList;
    private FirebaseUser user;



    //getting the context and department list with constructor
    public InterviewExperienceAdapter(Context myContext, List<InterviewExperience> placementList){
        this.myContext = myContext;
        this.placementList = placementList;
    }

    @NonNull
    @Override
    public InterviewExperienceAdapter.InterviewExperienceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //inflating and returning our view holder
        LayoutInflater layoutInflater = LayoutInflater.from(myContext);
        View view = layoutInflater.inflate(R.layout.card_interview_experience, null);

        return new InterviewExperienceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final InterviewExperienceViewHolder interviewExperienceViewHolder, final int position) {
        final InterviewExperience interviewExperience = placementList.get(position);
        user = FirebaseAuth.getInstance().getCurrentUser();

        interviewExperienceViewHolder.mStudName.setText(interviewExperience.getStudName());
        interviewExperienceViewHolder.mStudCompany.setText(interviewExperience.getCompanyName());

        interviewExperienceViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(myContext, UserProfileActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("StudentDetails",interviewExperience);
                myContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return placementList.size();
    }


    public static class InterviewExperienceViewHolder extends RecyclerView.ViewHolder {

        TextView mStudName,mStudCompany;
        CardView mCardView;


        public InterviewExperienceViewHolder(@NonNull final View itemView) {
            super(itemView);
            mStudName = itemView.findViewById(R.id.nameTextView);
            mStudCompany = itemView.findViewById(R.id.studCompanyName);
            mCardView = itemView.findViewById(R.id.cardView);

        }

    }



}
