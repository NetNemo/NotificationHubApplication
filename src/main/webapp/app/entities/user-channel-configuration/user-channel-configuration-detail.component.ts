import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserChannelConfiguration } from 'app/shared/model/user-channel-configuration.model';

@Component({
    selector: 'jhi-user-channel-configuration-detail',
    templateUrl: './user-channel-configuration-detail.component.html'
})
export class UserChannelConfigurationDetailComponent implements OnInit {
    userChannelConfiguration: IUserChannelConfiguration;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ userChannelConfiguration }) => {
            this.userChannelConfiguration = userChannelConfiguration;
        });
    }

    previousState() {
        window.history.back();
    }
}
