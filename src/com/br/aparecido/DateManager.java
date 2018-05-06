package com.br.aparecido;

import java.util.Calendar;

public abstract class DateManager {

	private Calendar minDateSelectable;
	private Calendar startDate;
	private Calendar finalDate;

	public DateManager() {
		minDateSelectable = Calendar.getInstance();

		minDateSelectable.set(1900, Calendar.JANUARY, 1, 0, 0, 0);
		minDateSelectable.clear(Calendar.HOUR);
		minDateSelectable.clear(Calendar.MINUTE);
		minDateSelectable.clear(Calendar.SECOND);
		minDateSelectable.clear(Calendar.MILLISECOND);

		startDate = Calendar.getInstance();
		finalDate = Calendar.getInstance();

		startDate.clear(Calendar.MINUTE);
		startDate.clear(Calendar.SECOND);
		startDate.set(Calendar.HOUR_OF_DAY, 0);
		startDate.clear(Calendar.MILLISECOND);

		finalDate.clear(Calendar.MINUTE);
		finalDate.clear(Calendar.SECOND);
		finalDate.set(Calendar.HOUR_OF_DAY, 0);
		finalDate.clear(Calendar.MILLISECOND);
	}

	public abstract boolean canGoToNextDate();

	public abstract boolean canGoToPreviousDate();

	public abstract void goToNextDate() throws DateManagerException;

	public abstract void goToPreviousDate() throws DateManagerException;

	public abstract void setSelectedDate(Calendar finalDate) throws DateManagerException;

	protected void setFinalDate(Calendar finalDate) {
		this.finalDate = finalDate;
	}

	public Calendar getMinDateSelectable() {
		return minDateSelectable;
	}

	protected void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public Calendar getFinalDate() {
		return finalDate;
	}
}
