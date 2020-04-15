import { Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild, HostListener } from '@angular/core';
import { User } from '../../models/user';
import { Session } from 'openvidu-browser';

@Component({
	selector: 'chat-component',
	templateUrl: './chat.component.html',
	styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
	@ViewChild('chatScroll') chatScroll: ElementRef;
	@ViewChild('chatInput') chatInput: ElementRef;

	@Input() session: Session;
	@Input() user: User;
	@Input() lightTheme: boolean;
	@Input()
	messageList: { userId: number; nickname: string; message: string }[] = [];

	_chatOpened: boolean;

	@Output() closeChat = new EventEmitter<any>();

	message: string;

	constructor() {}

	@HostListener('document:keydown.escape', ['$event'])
	onKeydownHandler(event: KeyboardEvent) {
		console.log(event);
		if (this._chatOpened) {
			this.close();
		}
	}

	ngOnInit() {}

	@Input('chatOpened')
	set isDisplayed(display: boolean) {
		this._chatOpened = display;
		if (this._chatOpened) {
			this.scrollToBottom();
			setTimeout(() => {
				this.chatInput.nativeElement.focus();
			});
		}
	}

	eventKeyPress(event) {
		if (event && event.keyCode === 13) {
			// Press Enter
			this.sendMessage();
		}
	}

	sendMessage(): void {
		if (this.user && this.message) {
			this.message = this.message.replace(/ +(?= )/g, '');
			if (this.message !== '' && this.message !== ' ') {
				const data = {
					userId: this.user.id,
					message: this.message,
					nickname: this.user.nickName
				};
				this.session.signal({
					data: JSON.stringify(data),
					type: 'chat'
				});
				this.message = '';
			}
		}
	}

	scrollToBottom(): void {
		setTimeout(() => {
			try {
				this.chatScroll.nativeElement.scrollTop = this.chatScroll.nativeElement.scrollHeight;
			} catch (err) {}
		}, 20);
	}

	close() {
		this.closeChat.emit();
	}
}
