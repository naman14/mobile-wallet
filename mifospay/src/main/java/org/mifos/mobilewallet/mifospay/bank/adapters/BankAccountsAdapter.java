package org.mifos.mobilewallet.mifospay.bank.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.mifos.mobilewallet.core.domain.model.BankAccountDetails;
import org.mifos.mobilewallet.mifospay.R;
import org.mifos.mobilewallet.mifospay.bank.ui.BankAccountsActivity;
import org.mifos.mobilewallet.mifospay.bank.ui.SetupUpiPinActivity;
import org.mifos.mobilewallet.mifospay.utils.AnimationUtil;
import org.mifos.mobilewallet.mifospay.utils.Constants;
import org.mifos.mobilewallet.mifospay.utils.DebugUtil;
import org.mifos.mobilewallet.mifospay.utils.Toaster;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ankur on 09/July/2018
 */

public class BankAccountsAdapter extends RecyclerView.Adapter<BankAccountsAdapter.ViewHolder> {

    private List<BankAccountDetails> mBankAccountDetailsList;
    private Activity mActivity;

    @Inject
    public BankAccountsAdapter(Activity activity) {
        mActivity = activity;
        mBankAccountDetailsList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bank_account,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final BankAccountDetails bankAccountDetails = mBankAccountDetailsList.get(position);
        holder.mTvBankName.setText(bankAccountDetails.getBankName());
        holder.mTvAccountHolderName.setText(bankAccountDetails.getAccountholderName());
        holder.mTvBranch.setText(bankAccountDetails.getBranch());
        holder.mTvType.setText(bankAccountDetails.getType());
        holder.mTvIfsc.setText(bankAccountDetails.getIfsc());
    }

    @Override
    public int getItemCount() {
        if (mBankAccountDetailsList != null) {
            return mBankAccountDetailsList.size();
        } else {
            return 0;
        }
    }

    public void setData(List<BankAccountDetails> bankAccountDetailsList) {
        this.mBankAccountDetailsList = bankAccountDetailsList;
        notifyDataSetChanged();
    }

    public BankAccountDetails getBankDetails(int position) {
        return mBankAccountDetailsList.get(position);
    }

    public void addBank(BankAccountDetails bankAccountDetails) {
        mBankAccountDetailsList.add(bankAccountDetails);
        notifyDataSetChanged();
        DebugUtil.log(mBankAccountDetailsList.size());
    }

    public void setBankDetails(int index, BankAccountDetails bankAccountDetails) {
        mBankAccountDetailsList.set(index, bankAccountDetails);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_bank_name)
        TextView mTvBankName;
        @BindView(R.id.tv_account_holder_name)
        TextView mTvAccountHolderName;
        @BindView(R.id.tv_branch)
        TextView mTvBranch;
        @BindView(R.id.tv_ifsc)
        TextView mTvIfsc;
        @BindView(R.id.tv_type)
        TextView mTvType;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}