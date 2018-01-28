package sugar.free.sightremote.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import sugar.free.sightparser.applayer.descriptors.HistoryBolusType;
import sugar.free.sightremote.R;
import sugar.free.sightremote.database.BolusDelivered;
import sugar.free.sightremote.database.EndOfTBR;
import sugar.free.sightremote.utils.UnitFormatter;

public class TBRAdapter extends RecyclerView.Adapter<TBRAdapter.ViewHolder> {

    @Getter
    @Setter
    private List<EndOfTBR> endOfTBRs;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tbr, parent, false);
        return new TBRAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EndOfTBR endOfTBR = endOfTBRs.get(position);
        int minutes = endOfTBR.getDuration() % 60;
        int hours = (endOfTBR.getDuration() - minutes) / 60;
        holder.amount.setText(holder.amount.getResources().getString(R.string.history_tbr_amount,
                endOfTBR.getAmount(), hours, minutes));
        holder.dateTime.setText(DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT).format(endOfTBR.getDateTime()));
    }

    @Override
    public int getItemCount() {
        return endOfTBRs == null ? 0 : endOfTBRs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView dateTime;
        private TextView amount;

        public ViewHolder(View itemView) {
            super(itemView);
            dateTime = itemView.findViewById(R.id.date_time);
            amount = itemView.findViewById(R.id.amount);
        }
    }

}