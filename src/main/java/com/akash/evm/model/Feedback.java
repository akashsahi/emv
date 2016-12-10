package com.akash.evm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.akash.evm.enums.FeedbackStatusEnum;

@Entity
@Table(name = "FEEDBACK")
public class Feedback extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 91441109973892809L;
	@SequenceGenerator(sequenceName = "FEEDBACK_SEQ", name = "FEEDBACK_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "FEEDBACK_SEQ", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "FEEDBACK_ID")
	private Long feedbackId;
	@Column(name = "FEEDBACK", nullable = false)
	private String feedback;
	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private FeedbackStatusEnum status;

	public Long getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Long feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public FeedbackStatusEnum getStatus() {
		return status;
	}

	public void setStatus(FeedbackStatusEnum status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Feedback [");
		if (feedbackId != null) {
			builder.append("feedbackId=");
			builder.append(feedbackId);
			builder.append(", ");
		}
		if (feedback != null) {
			builder.append("feedback=");
			builder.append(feedback);
			builder.append(", ");
		}
		if (status != null) {
			builder.append("status=");
			builder.append(status);
		}
		builder.append("]");
		return builder.toString();
	}

}
