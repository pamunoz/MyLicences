package com.pfariasmunoz.mylicences.licencedetail;

/**
 * Created by Pablo Farias on 06-10-17.
 */

public interface LicenceDetailContract {
    interface View {

        void setProgressIndicator(boolean active);
        void showLicenceNotFoundError();
        void setLicenceNumber(String number);
        void setDuration(int duration);
        void setStartDate(String formatedDate);
        void setEndDate(String formatedDate);

    }

    interface UserActionListener {

    }
}
