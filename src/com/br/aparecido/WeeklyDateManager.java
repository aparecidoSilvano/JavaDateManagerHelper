package com.br.aparecido;

import java.util.Calendar;

public class WeeklyDateManager extends DateManager {
	
	public WeeklyDateManager() {
		super();
		
		Calendar startDate = getStartDate();
		startDate.add(Calendar.DAY_OF_YEAR, -6);

		setStartDate(startDate);
	}

	@Override
	public boolean canGoToNextDate() {
		Calendar today = Calendar.getInstance();

		today.clear(Calendar.MINUTE);
		today.clear(Calendar.SECOND);
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.clear(Calendar.MILLISECOND);

		return getFinalDate().compareTo(today) < 0;
	}

	@Override
	public boolean canGoToPreviousDate() {
		return getFinalDate().compareTo(getMinDateSelectable()) > 0;
	}

	@Override
	public void goToNextDate() throws DateManagerException {
		if (canGoToNextDate()) {
			
			Calendar startDate = getStartDate();
			startDate.add(Calendar.DAY_OF_YEAR, 1);
			setStartDate(startDate);
			
			Calendar finalDate = getFinalDate();
			finalDate.add(Calendar.DAY_OF_YEAR, 1);
			setFinalDate(finalDate);
			
		} else {
			throw new DateManagerException("Não é possível avançar para uma data além de hoje.");
		}
	}

	@Override
	public void goToPreviousDate() throws DateManagerException {
		if (canGoToPreviousDate()) {
			
			Calendar startDate = getStartDate();
			startDate.add(Calendar.DAY_OF_YEAR, -1);

			setStartDate(startDate);
			
			Calendar finalDate = getFinalDate();
			finalDate.add(Calendar.DAY_OF_YEAR, -1);
			setFinalDate(finalDate);	
			
		} else {
			throw new DateManagerException("Não é possível regredir para uma data anterior a 01/01/1900.");
		}
	}

	@Override
	public void setSelectedDate(Calendar finalDate) throws DateManagerException {
		Calendar today = Calendar.getInstance();

		today.clear(Calendar.MINUTE);
		today.clear(Calendar.SECOND);
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.clear(Calendar.MILLISECOND);

		if (finalDate.compareTo(getMinDateSelectable()) >= 0 && finalDate.compareTo(today) <= 0) {
			
			setFinalDate(finalDate);

			Calendar startDate = Calendar.getInstance();
			startDate.setTime(finalDate.getTime());
			startDate.add(Calendar.DAY_OF_YEAR, -6);

			setStartDate(startDate);
			
		} else {
			throw new DateManagerException("Data Inválida " + finalDate.getTime().toString());
		}
	}
}
